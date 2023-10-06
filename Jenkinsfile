def mods
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                scmSkip(deleteBuild: true, skipPattern:'.*\\[ci skip\\].*')
            }
        }

        stage('Create Mod List') {
            steps {
                script {
                    // you may create your list here, lets say reading from a file after checkout
                    mods = ["nompmenu","update-me"]
                }
            }
        }

        stage('Mods') {
            steps {
                script {
                    for (int i = 0; i < mods.size(); i++) {
                        stage(mods[i]) {
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