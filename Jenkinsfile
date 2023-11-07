pipeline {
    agent any

   environment {
        registry = "gloria6056/jenkins-pipeline"
        registryCredential = 'dockerhub_id'
        dockerImage = ''
    }
    stages {
        stage ('GIT') {
            steps {
                echo "Getting project from Git"
                git branch: "Gloria", 
                    credentialsId: 'd2461957-c249-41f9-87e2-49dcec9e642b',
                    url: "https://github.com/Sarah47393/Ski-Station-Project1.git";
            }
        }

       stage ('Build Artifact'){

            steps{
                echo "Building Artifact with Maven..."
                sh "mvn clean package -DskipTests=true"
                archive 'target/*.jar'
            }
       }

       stage('SonarQube'){
            steps{
                withSonarQubeEnv('SonarQube'){
                    echo "Running SonarQube analysis..."
                    sh "mvn sonar:sonar -Dsonar.projectKey=maven-jenkins-pipeline -Dsonar.host.url=http://192.168.33.10:9000 -Dsonar.login=e51d1dac06a170aa4f4af598de60eea87d83b4fc"
                    
            }
                }
                
        }

        stage('JUnit/Mockito') {
            steps {
              script{
                    echo "Test unitaire..."
                    sh 'mvn test'
                }
            }
            
        }

        stage("Deploy to nexus") {
            steps {
                echo "Deploy to nexus..."
                sh "mvn deploy -DskipTests=true"
            }
        }

        stage("Build Docker image") {
            steps {
                script{
                    echo "Building Docker Image..."
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
                }
                
            }
        }

        stage('Deploy our image') {
            steps{
            script {
            echo "Push image to Docker Hub...."
            docker.withRegistry( '', registryCredential ) {
            dockerImage.push()
                       }
                 }
               }
        }

       


       
                            

       
       
    }
}
