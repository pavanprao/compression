pipeline {
	agent {
		docker {
			image "compression-1.0.0-RELEASE"
			args "-v $HOME/.m2:/root/.m2"
		}
	}
	
	node {
	    stages {
		    stage "Checkout" 
		    git "https://git.enstage-sas.com/eguard/compression.git"
		    
		    dir("compression") {
		        stage ("Compile") {
		        	sh "./mvnw clean install -DskipTests"          
		        }
		    }
		    
		    stage("Deploy") {
                sh "pid=\$(lsof -i:8080 -t); kill -TERM \$pid "
                  + "|| kill -KILL \$pid"
                withEnv(["JENKINS_NODE_COOKIE=dontkill"]) {
                    sh "nohup ./mvnw spring-boot:run -Dserver.port=8080 &"
                }   
            }
		}
	}
}