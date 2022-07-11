pipeline {
    triggers {
  pollSCM('* * * * *')
    }
   agent {
        docker { image 'maven:3.8.6-openjdk-18' }
   }
    tools {
  maven 'M2_home'
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
                sh 'mvn install -DskipTests'
                sh 'mvn package -DskipTests'
            }
        }
          
         
          stage('deploy') {
            steps {
                echo 'deployement'
                
            }
        }
    }
}
