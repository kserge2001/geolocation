pipeline {

    agent any

    stages{
        stage ('Git Checkout'){

            steps{
                git branch: 'main', url: 'https://github.com/henrykrop2022/geolocation-23.git'
            }  
        }
        stage('UNIT Testing'){

            steps{
                sh 'mvn test'
            }  
        }
        stage('Integration testing'){

            steps{
                sh 'mvn verify-DskipUnitTests'
            } 
        } 
    }
}