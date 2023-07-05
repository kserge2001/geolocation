pipeline {
    triggers {
        pollSCM('* * * * *')
    }
    agent any
    tools {
        maven 'Maven 3.6.3'
    }
    environment {
        registry = '076892551558.dkr.ecr.us-east-1.amazonaws.com/jenkins'
        registryCredential = 'aws_ecr_id'
        dockerImage = ''
    }
    stages {
        stage("build & SonarQube analysis") {
            agent {
                docker { image 'maven:3.8.6-openjdk-11-slim' }
            }
            steps {
                withSonarQubeEnv('SonarServer') {
                    sh 'mvn sonar:sonar -Dsonar.projectKey=kserge2001_geolocation -Dsonar.java.binaries=.'
                }
            }
        }
        stage('Check Quality Gate') {
            steps {
                echo 'Checking quality gate...'
                script {
                    timeout(time: 20, unit: 'MINUTES') {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            error "Pipeline stopped because of quality gate status: ${qg.status}"
                        }
                    }
                }
            }
        }
        stage('Maven Package') {
            steps {
                sh 'mvn clean install package -DskipTests'
            }
        }
        stage('Build Image') {
            steps {
                script {
                    def mavenPom = readMavenPom file: 'pom.xml'
                    dockerImage = docker.build "${registry}:${mavenPom.version}"
                }
            }
        }
        stage('Deploy Image') {
            steps {
                script {
                    docker.withRegistry("https://${registry}", "ecr:us-east-1:${registryCredential}") {
                        dockerImage.push()
                    }
                }
            }
        }
    }
}
