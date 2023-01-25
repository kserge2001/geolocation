pipeline {

   tools { 
        maven 'JenkinsMaven' 
        jdk 'JenkinsJDK'
   }

    stages{
        stage ('Git Checkout'){

            steps{
                git branch: 'main', url: 'https://github.com/henrykrop2022/geolocation-23.git'
            }  
        }
        stage('UNIT Testing'){

            steps{
                sh 'mvn clean package'
            
            }
        }
        stage('Integration testing'){

            steps{
                sh 'mvn verify-DskipUnitTests'
            } 
        } 
    }
}