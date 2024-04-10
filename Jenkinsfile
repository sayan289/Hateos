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
        stage('Build docker image') {
            steps {
                script {
                    sh 'docker build -t sayanadhikary/springboot-app .'
                }
            }
        }
        stage('Push image to docker hub') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                        sh 'docker login -u sayanadhikary -p ${dockerhubpwd}'
                    }
                    sh 'docker push sayanadhikary/springboot-app'
                }
            }
        }
        stage('deploy k8s'){
            steps{
                script{
                    kubeconfig(caCertificate: '', credentialsId: 'mykubeconfig', serverUrl: 'https://192.168.49.2:8443'){
                        sh 'kubectl delete service/springboot-crud-svc --ignore-not-found=true'
                        sh 'kubectl delete deployment.apps/springboot-crud-deployment --ignore-not-found=true'
                        sh 'kubectl apply -f mysql-secret.yaml'
                        sh 'kubectl apply -f mysql-storage.yaml'
                        sh 'kubectl apply -f mysql-deployment.yaml'
                        sh 'kubectl apply -f app-deployment.yaml'
                        sh 'kubectl get pods'
                    }
                }
            }
        }
    }
}
