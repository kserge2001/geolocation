pipeline{

    agent any
    tools {
        maven "M2_HOME"
    }


    stages{
        stage('maven build'){
            steps{
                sh 'mvn clean install package'
            }
        }
    

        stage('uplod articfact'){
            steps{
               script{
                def mavenPom = readMavenPom file: 'pom.xml' 
                nexusArtifactUploader artifacts:
                 [[artifactId: "${mavenPom.ARTIFACTID}", 
                 classifier: '',
                 file: "target/${mavenPom.ARTIFACTID}-${mavenPom.VERSION}.${mavenPom.PACKAGING}",
                  type: "${mavenPom.PACKAGING}"]],
                   credentialsId: 'NexusID', 
                   groupId: "${mavenPom.GROUPID}", 
                   nexusUrl: '192.168.0.87:8081',
                    nexusVersion: 'nexus3', 
                    protocol: 'http', 
                    repository: 'bio-medic-app', 
                    version: "${mavenPom.VERSION}"
               }
            }
        }
       
      
    }
}