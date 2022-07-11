pipeline {
    triggers {
  pollSCM('* * * * *')
    }
   agent {
        docker { image 'maven:3.8.6-openjdk-18' }
   }
    tools {
  maven 'maven3'
}
   

    stages {
        stage("build & SonarQube analysis") {
            
            steps {
              withSonarQubeEnv('sonar') {
                  sh 'mvn sonar:sonar'
              }
            }
          }
        
         
        stage('maven package') {
            steps {
                sh 'mvn clean'
                sh 'mvn install'
                sh 'mvn package'
            }
        }
          stage('test') {
            steps {
               sh 'mvn test'
                
            }
        }
        
         
          stage('deploy') {
            steps {
                echo 'deployement'
                
            }
        }
    }
}
