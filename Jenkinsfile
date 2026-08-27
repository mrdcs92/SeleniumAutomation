pipeline {
    agent any

	parameters {
		
		choice(
			name: 'TEST_SUITE',
			choices: [
				'Regression',
				'Purchase',
				'ErrorValidation',
				'Cucumber',
				'DbTest'
			],
			description: 'Select the Maven test profile to execute'
		)
		
		choice(
			name: 'BROWSER',
			choices: [
				'chrome',
				'edge',
				'firefox',
			],
			description: 'Browser to use for Selenium'
		)
		
		choice(
			name: 'HEADLESS',
			choices: [
				' ',
				'headless'
			],
			description: 'Choice of running headless or not'
		)
		
	}
	
    stages {

        stage('Verify Environment') {
            steps {
                bat 'java -version'
                bat 'mvn -version'
            }
        }

        stage('Run Tests') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'mysql-qa-db',
                        usernameVariable: 'DB_USERNAME',
                        passwordVariable: 'DB_PASSWORD'
                    )
                ]) {
                    bat "mvn clean test -P${params.TEST_SUITE} -Dbrowser=${params.BROWSER}${params.HEADLESS}"
                }
            }
        }
    }

    post {
		always {
        	echo 'Archiving Extent Reports and screenshots...'

        	archiveArtifacts artifacts: 'reports/**',
        	allowEmptyArchive: true
        	
        	publishHTML([
				allowMissing: false,
				alwaysLinkToLastBuild: true,
				keepAll: true,
				reportDir: 'reports',
				reportFiles: 'index.html',
				reportName: 'Extent Report'
			])
    	}
        success {
            echo "Test suite ${params.TEST_SUITE} passed."
        }

        failure {
            echo "Test suite ${params.TEST_SUITE} failed."
        }
    }
}