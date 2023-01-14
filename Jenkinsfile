pipeline {
   agent any
    tools {
        maven 'M2_HOME'
   }
   stages{
    stage ('maven build'){
        steps{
            sh 'mvn clean install package'
            }
        }
        stage ('upload artifact'){
             steps{
                script {
                    def mavenPOM = readMavenPOM file: 'pom.xml'
            nexusArtifactUploader artifacts:
             [[artifactId: "${mavenPOM.artifactId}", 
                classifier: '', 
                  file: "target/${mavenPOM.artifactId}-${mavenPOM.version}.${mavenPOM.packaging}", 
                    type: "${mavenPOM.packaging}"]], 
                       credentialsId: "NexusI", 
                          groupId: '${mavenPOM.groupId}', 
                            nexusUrl: '192.168.78.112:8081', 
                              nexusVersion: 'nexus3', 
                                protocol: 'http', 
                                  repository: 'maven-nexus-repo',
                                    version: "${mavenPOM.version}"
                }
            }
       }
    }
}