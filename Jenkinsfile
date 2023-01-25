pipeline {

    agent any

    stages{
        stage ('Git Checkout'){

            steps{
                git branch: 'main', url: 'https://github.com/henrykrop2022/geolocation-23.git'
            }  
        }
        stage ('Unit Testing'){

            steps{
                sh 'mvn test'
            }  
        }
        stage ('aiantergration testing'){

            steps{
                sh 'mvn verify-DskipunitTests'
            }  
    }
}