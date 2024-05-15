pipeline {
    agent any
    tools {
        maven 'maven'
    }
    stages {
        stage('Build Maven Project') {
            steps {
                // Print Maven version to verify
                sh 'mvn --version'

                // Checkout the Git repository
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/sayan289/Hateos']])

                // Build the Maven project
                sh 'mvn clean install'
            }
        }
        stage('Junit'){
                    steps{
                        sh './mvnw test'
                    }
                    post{
                        always{
                            junit '**/target/surefire-reports/TEST-*.xml'
                        }
                    }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    // Build the Docker image
                    sh 'docker build -t sayanadhikary/springboot-app .'
                }
            }
        }
        stage('Push Image to Docker Hub') {
            steps {
                script {
                    // Login to Docker Hub
                    withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                        sh 'docker login -u sayanadhikary -p ${dockerhubpwd}'
                    }
                    // Push the Docker image
                    sh 'docker push sayanadhikary/springboot-app'
                }
            }
        }
    }
}
