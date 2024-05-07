pipeline {
    agent any

    environment {
        //Use Pipeline Utility Steps plugin to read information from pom.xml into env variables
        IMAGE = 'spokay/auth-template-app'
        VERSION = readMavenPom().getVersion()
        DOCKER_HUB_CREDENTIALS = 'docker-hub-credentials'
        REGISTRY_URL = 'docker.io'
    }
    stages {
        stage('Get Code') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'pipelineScript', url: 'https://github.com/Spokay/auth-template-api.git'
            }
        }
        stage('Build and push image') {

            steps {
                script {
                    sh('docker build -t ${IMAGE}:${VERSION} .')
                }
            }

            post {
                success {
                    script {
                        withCredentials([usernamePassword(credentialsId: DOCKER_HUB_CREDENTIALS, usernameVariable: 'DOCKER_HUB_USERNAME', passwordVariable: 'DOCKER_HUB_PASSWORD')]) {
                            sh('echo $DOCKER_HUB_PASSWORD | docker login -u $DOCKER_HUB_USERNAME --password-stdin')
                            sh('docker tag $IMAGE:$VERSION $REGISTRY_URL/$IMAGE:$VERSION')
                            sh('docker push $REGISTRY_URL/$IMAGE:$VERSION')
                        }
                    }
                }
            }
        }
    }
}
