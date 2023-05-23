pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Roman-Pshenychniak/lab1-devops'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("my-image")
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    withDockerRegistry([credentialsId: 'romanpshenychniak', url: 'https://localhost:8080']) {
                        dockerImage.push()
                    }
                }
            }
        }
    }
}
