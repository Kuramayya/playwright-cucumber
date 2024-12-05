pipeline {
    agent any
    tools { 
    		maven 'Maven 3.6.3' // Name configured in the Global Tool Configuration
    	}
    environment { // If you need to set any environment variables, you can define them here 
        PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD = '1'

    }

    stages {
       // stage('Checkout') {
       //     steps {
                // Checkout the code from the Git repository
        //        git 'https://github.com/Kuramayya/playwright-cucumber.git'
        //    }
        //}
        stage('Install Chrome') { 
            steps { // Install Chrome 
                sh ''' #!/bin/bash 
                # Add Google's official repository to your package manager 
                wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | sudo apt-key add - 
                echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" | sudo tee /etc/apt/sources.list.d/google-chrome.list 
                # Update the package list and install Chrome 
                sudo apt-get update 
                sudo apt-get install -y google-chrome-stable ''' 
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
