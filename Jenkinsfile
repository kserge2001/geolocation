pipeline {
<<<<<<< HEAD

    agent any
     tools {
  maven 'M2_HOME'
     }

    stages{
        stage('Git Checkout'){
            steps{
                git branch: 'main', url: 'https://github.com/henrykrop2022/helloworld_jan_23.git'
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
        stage ('SonarQube analysis'){
            steps {
                script{
                withSonarQubeEnv(credentialsId: 'sonar-api-key') {
                    sh 'mvn clean package sonar:sonar'
                }
               }
            }
        }
=======

    agent any
   
    stages{
        stage ('Git Checkout'){

            steps{
                git branch: 'main', url: 'https://github.com/henrykrop2022/geolocation-23.git'
            }  
        }
        stage('UNIT Testing'){

            steps{
                sh 'mvn clean install package'
            
            }
        }
        stage('Integration testing'){

            steps{
                sh 'mvn verify-DskipUnitTests'
            } 
        } 
>>>>>>> cbf8e1d5f3479ebe870304ec23c162577110e818
    }
}