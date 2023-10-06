def mods = ["nompmenu","update-me"]
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                scmSkip(deleteBuild: true, skipPattern:'.*\\[ci skip\\].*')
            }
        }

        stage('Mods') {
            steps {
                script {
                    for (int i = 0; i < mods.length; i++) {
                        stage("Build ${mods[i]}") {
                            when {
                                anyOf {
                                    changeset "${mods[i]}/**"
                                }
                            }
                            steps {
                                withGradle {
                                    sh './gradlew :${mods[i]}:remapJar'
                                }
                                archiveArtifacts artifacts: '${mods[i]}/build/libs/*.jar', fingerprint: true, followSymlinks: false, onlyIfSuccessful: true
                            }
                        }
                    }
                }
            }
        }
    }
}