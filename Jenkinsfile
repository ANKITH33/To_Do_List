pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    environment {
        DOCKER_IMAGE = "ankith33/imt2023075-se"
        DOCKER_CREDENTIALS = "dockerhub"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/ANKITH33/To_Do_List'
            }
        }

        stage('Build (Maven)') {
            steps {
                bat 'mvn -B clean package'
            }
        }

        stage('Test (Maven)') {
            steps {
                bat 'mvn -B test'
            }
        }

        stage('Docker Build') {
            steps {
                bat "docker build -t %DOCKER_IMAGE%:latest ."
            }
        }

        stage('Docker Login & Push') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: env.DOCKER_CREDENTIALS,
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    bat """
                        echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin
                        docker push %DOCKER_IMAGE%:latest
                    """
                }
            }
        }
    }

    post {
        always {
            bat 'docker logout || exit 0'
        }
    }
}
