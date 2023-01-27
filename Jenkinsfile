pipeline {
    agent any
     tools {
  maven 'M2_HOME'
     }

    stages{
        stage('Git Checkout'){
            steps{
                git branch: 'main', url: 'https://github.com/henrykrop2022/geolocation-23.git'
            }
        }
        stage('UNIT Testing'){
            steps{
               sh 'mvn test'
               
            }
        }
        stage('Integration Testing'){
            steps{
               sh 'mvn verify -DskipUnitTests'  
            }
        }
        stage('Maven Build'){
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Quality-Gate'){

            steps {
                script{
                waitForQualityGate abortPipeline: false  
                }  
            }   
        }
        stage('SonarQube Analysis'){
            
            steps {
                script {
                    withSonarQubeEnv(credentialsId: 'dev-utrains') {  
                        sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.1.2184:sonar'
                        sh 'mvn clean package sonar:sonar'
                    }
                }  
            }
        }
        
    }
}