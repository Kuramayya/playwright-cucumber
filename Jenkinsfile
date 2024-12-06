pipeline {
    agent any
    tools { 
    		maven 'Maven 3.6.3' // Name configured in the Global Tool Configuration
    	}
    stages {
       // stage('Checkout') {
       //     steps {
                // Checkout the code from the Git repository
        //        git 'https://github.com/Kuramayya/playwright-cucumber.git'
        //    }
        //}
        stage('Install Chromium') { 
       steps { 
           // Install Chromium using Playwright CLI 
           sh 'npx playwright install chrome' 
       }
   }
        stage('Build') {
            steps {
                // Clean and package the project
                sh 'mvn clean package'
            }
        }
        stage('Run Tests') {
            steps {
                // Execute the Cucumber tests
                sh 'mvn test'
            }
        }
    }
    post {
        always {
            // Publish test results
            junit 'target/surefire-reports/*.xml'
            cucumber 'target/cucumber-reports/Cucumber.json'
        }
        cleanup {
            // Clean up the workspace
            deleteDir()
        }
    }
}
