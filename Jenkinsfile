pipeline {
    agent any

    environment {
        //Use Pipeline Utility Steps plugin to read information from pom.xml into env variables
        IMAGE = 'spokay/auth-template-app'
        VERSION = readMavenPom().getVersion()
        DOCKER_HUB_CREDENTIALS = 'docker-hub-credentials'
        REGISTRY_URL = 'https://registry.hub.docker.com'
    }
    stages {
        stage('Get Code') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'pipelineScript', url: 'https://github.com/Spokay/auth-template-api.git'
            }
        }
        stage('Build') {
            agent any

            steps {
                script {
                    docker.withRegistry(REGISTRY_URL, DOCKER_HUB_CREDENTIALS) {
                        def dockerImage = docker.build("--build-arg PORT=8081", "${IMAGE}:${VERSION}")
                        dockerImage.push()
                    }
                }
            }

            post {
                success {
                    script {
                        docker.withRegistry(REGISTRY_URL, DOCKER_HUB_CREDENTIALS) {
                            dockerImage.push()
                        }
                    }
                }
            }
        }

        stage('Deploy') {
            agent any

            steps {
                sh """
                docker pull ${IMAGE}:${VERSION}
                docker run -e 'PORT=8081' -d ${IMAGE}:${VERSION}
                """
            }
        }
    }
}
