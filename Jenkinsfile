pipeline {
    agent any
    stages {
        stage('Build and Test') {
            steps {
                sh 'xvfb-run -a ./gradlew clean assemble test'
                sh 'export RBENV_ROOT=/usr/local/rbenv;export PATH="$RBENV_ROOT/bin:$PATH";export RUBY_BUILD_MIRROR_URL="https://artifactory.global.dish.com/artifactory/rbenv-mirror/";cd acceptance;export SPRING_PROFILES_ACTIVE="ci";/usr/local/rbenv/shims/bundle install --path ./vendor;/usr/local/rbenv/shims/bundle exec rspec'
            }
        }
        stage('Tag and Push') {
            steps {
                sh 'export CF_PASSWORD="201[]ZD%Ja.}";export TAG="BUILD_SUCCESS_$(date \'+%Y-%m-%d--%H-%M-%S\')";git tag "$TAG";git push "https://svc_dev_cf:$CF_PASSWORD@gitlab.global.dish.com/pivotal-ofm/services/beacon-des-service.git" "$TAG"'
            }
        }
        stage('Bundle Install and Pack') {
            steps {
                sh 'export RBENV_ROOT=/usr/local/rbenv;export PATH="$RBENV_ROOT/bin:$PATH";export RUBY_BUILD_MIRROR_URL="https://artifactory.global.dish.com/artifactory/rbenv-mirror/";cd acceptance;export SPRING_PROFILES_ACTIVE="ci";/usr/local/rbenv/shims/bundle install --path vendor/cache;/usr/local/rbenv/shims/bundle pack --all'
            }
        }
        stage('Push to CF') {
            steps {
                sh 'CheckInstanceStatus(){ set +x; InstanceEnv=$(curl -X GET -H"Accept:text/plain" $1 2>&1); if [[ $InstanceEnv == *"state=On"* ]]; then echo 0; else echo 1; fi };DeployToCF(){ url=$1; org=$2; domain=$3; manifest=$4; cf api $url --skip-ssl-validation; cf auth svc_dev_cf 201[]ZD%Ja.}; cf target -o $org -s Pivotal-OFM; cf push -d $domain -f $manifest; cf push -d $domain -f $manifest; };IsGreenUp=$(CheckInstanceStatus https://jenkins.global.dish.com/job/VEA%20Buttons/job/development%20Green%20State/lastSuccessfulBuild/injectedEnvVars/export); IsBlueUp=$(CheckInstanceStatus https://jenkins.global.dish.com/job/VEA%20Buttons/job/development%20Blue%20State/lastSuccessfulBuild/injectedEnvVars/export); if [ $IsGreenUp -eq 1 ] && [ $IsBlueUp -eq 1 ]; then set -x; echo "Both Green and Blue are down. Exiting Build as failure."; exit 1; fi; if [ $IsGreenUp -eq 0 ]; then echo "Green is up. Deploying to Green"; DeployToCF https://api.sys.internal-green.dev.mer.cfdish.io DEVELOPMENT-GREEN pivotal-ofm.apps.internal-green.dev.mer.cfdish.io manifest-review.yml; fi; if [ $IsBlueUp -eq 0 ]; then echo "Blue is up. Deploying to Blue"; DeployToCF https://api.sys.internal-blue.dev.mer.cfdish.io DEVELOPMENT-BLUE pivotal-ofm.apps.internal-blue.dev.mer.cfdish.io manifest-review.yml; fi'
            }
        }
    }
}
