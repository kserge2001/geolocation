pipeline {
    triggers {
  pollSCM('* * * * *')
    }
    agent any
    tools {
  maven 'M2_HOME'
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
