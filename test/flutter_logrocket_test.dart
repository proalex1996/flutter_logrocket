import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_logrocket/flutter_logrocket_platform_interface.dart';
import 'package:flutter_logrocket/flutter_logrocket_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockFlutterLogrocketPlatform
    with MockPlatformInterfaceMixin
    implements FlutterLogrocketPlatform {
  @override
  void initLogrocket(String appId) {
    // TODO: implement initLogrocket
  }

  @override
  void sendGetRequest(String url) {
    // TODO: implement sendGetRequest
  }
}

void main() {
  final FlutterLogrocketPlatform initialPlatform =
      FlutterLogrocketPlatform.instance;

  test('$MethodChannelFlutterLogrocket is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelFlutterLogrocket>());
  });
}
