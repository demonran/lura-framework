def project = ''
def host = ''
pipeline {
   agent any
    parameters {
        choice(
            description: "选择部署的服务",
            name: "project",
            choices: ['lura-gateway-server:lura-gateway','lura-framework-auth-server:lura-auth']
        )
    }

   stages {
        stage("parse parameters") {
           steps {
               script {
                   project = "${params.project}".split(":")[0]
                   host = "${params.project}".split(":")[1]
               }
           }
        }
        stage("dockerize") {
            environment {
                   DOCKER_REG = "registry.cn-chengdu.aliyuncs.com/lura"
                   DOCKER_REPOSITORY = "${DOCKER_REG}/${project}"
                   DOCKER_IMAGE = "${DOCKER_REPOSITORY}:build-${BUILD_NUMBER}"
            }
            steps {
                withCredentials([usernamePassword(credentialsId: 'aliyun', passwordVariable: 'password', usernameVariable: 'username')]) {
                        sh "echo ${password} |sudo docker login --username=${username}  registry.cn-chengdu.aliyuncs.com --password-stdin"
                        sh "sudo docker build -t ${DOCKER_IMAGE} -f ${project}/Dockerfile ."
                        sh "sudo docker push ${DOCKER_IMAGE}"
                        sh "sudo docker rmi ${DOCKER_IMAGE}"
                }
            }
        }

        stage('Deploy') {
            steps {
                sh "export PROJECT=${project};export HOST=${host}; envsubst < deployment.yml"
                sh "export PROJECT=${project};export HOST=${host}; envsubst < deployment.yml | sudo kubectl apply -f -"
           }
        }
   }
}
