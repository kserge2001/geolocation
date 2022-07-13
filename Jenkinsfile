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
                 [[artifactId: "${POM_ARTIFACTID}", 
                classifier: '',
                 file: "target/${POM_ARTIFACTID}-${POM_VERSION}.${POM_PACKAGING}",
                  type: "${POM_PACKAGING}"]],
                   credentialsId: 'NexusID', 
                   groupId: "${POM_GROUPID}", 
                   nexusUrl: '192.168.0.87:8081',
                    nexusVersion: 'nexus3', 
                    protocol: 'http', 
                    repository: 'bio-medic-app', 
                    version: "${POM_VERSION}"
               }
            }
        }
       
        stage('list the dir'){
            steps{
                sh 'ls'
            }
        }
    }
}