pipeline {
    agent any

    environment {
        //Use Pipeline Utility Steps plugin to read information from pom.xml into env variables
        IMAGE = 'spokay/auth-template-app'
        VERSION = readMavenPom().getVersion()
        DOCKER_HUB_CREDENTIALS = 'docker-hub-credentials'
        REGISTRY_URL = 'https://index.docker.io/v1/'
    }
    stages {
        stage('Get Code') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'pipelineScript', url: 'https://github.com/Spokay/auth-template-api.git'
            }
        }
        stage('Build') {

            steps {
                script {
                    sh """
                    docker build -t ${IMAGE}:${VERSION} .
                    """
                }
            }

            post {
                success {
                    script {
                        withCredentials([usernamePassword(credentialsId: DOCKER_HUB_CREDENTIALS, usernameVariable: 'DOCKER_HUB_USERNAME', passwordVariable: 'DOCKER_HUB_PASSWORD')]) {
                            sh """
                            echo ${DOCKER_HUB_PASSWORD} | docker login -u ${DOCKER_HUB_USERNAME} --password-stdin
                            docker tag ${IMAGE}:${VERSION} ${REGISTRY_URL}/${IMAGE}:${VERSION}
                            docker push ${REGISTRY_URL}/${IMAGE}:${VERSION}
                            """
                        }
                    }
                }
            }
        }

        /* stage('Deploy') {
            agent any

            steps {
                sh """
                docker pull ${IMAGE}:${VERSION}
                docker run -e 'PORT=8081' -d ${IMAGE}:${VERSION}
                """
            }
        } */
    }
}
