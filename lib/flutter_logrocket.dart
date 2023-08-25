import 'flutter_logrocket_platform_interface.dart';

class FlutterLogrocket {
  void initLogrocket({required String appId}) async {
    FlutterLogrocketPlatform.instance.initLogrocket(appId);
  }

  void sendGetRequest({required String url}) async {
    FlutterLogrocketPlatform.instance.sendGetRequest(url);
  }
}
