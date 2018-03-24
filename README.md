# slack-error-bot

- 서비스에서 error 발생 시 slack에 에러 로그를 남길 수 있도록 개발한 테스트 봇
- slack-webhook 라이브러리를 사용함
- src/main/webapp/resources/config.properties 중 log.slack.webHookUrl 프로퍼티에는 획득한 webhook url을 작성한다
- [webhook url 취득방법](https://support.smrtbeat.com/hc/ko/articles/207995083-Slack-%EB%B0%9C%EC%86%A1%EC%97%90-%EC%82%AC%EC%9A%A9%ED%95%A0-Webhook-URL%EC%9D%80-%EC%96%B4%EB%96%BB%EA%B2%8C-%ED%9A%8D%EB%93%9D%ED%95%A9%EB%8B%88%EA%B9%8C-)  
