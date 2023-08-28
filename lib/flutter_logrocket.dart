import 'flutter_logrocket_platform_interface.dart';

class FlutterLogrocket {
  void initLogrocket({required String appId}) async {
    FlutterLogrocketPlatform.instance.initLogrocket(appId);
  }

  void identifyUser({
    required String userId,
    String? name,
    String? email,
  }) async {
    FlutterLogrocketPlatform.instance.identifyUser(userId, name, email);
  }
}
