pipeline {
    agent any

    environment {
        //Use Pipeline Utility Steps plugin to read information from pom.xml into env variables
        IMAGE = readMavenPom().getArtifactId()
        VERSION = readMavenPom().getVersion()
    }
    stages {
        stage('Build') {
            agent any


            steps {
                // Get some code from a GitHub repository
//                 git branch: 'develop', credentialsId: '1b592148-a810-462d-84e1-d529b4b655b1', url: 'https://github.com/Spokay/auth-template-api.git'

                sh """
                docker build --build-arg="PORT=8081" -t ${IMAGE} .
                """
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    sh """
                    docker tag ${IMAGE} ${IMAGE}:${VERSION}
                    docker push ${IMAGE}:${VERSION}
                    """
                }
            }
        }

        stage('Deploy') {
            agent any

            steps {
                sh """
                docker pull ${IMAGE}:${VERSION}
                docker run -e "PORT=8081" ${IMAGE}:${VERSION}
                """
            }
        }
    }
}
