import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'flutter_logrocket_method_channel.dart';

abstract class FlutterLogrocketPlatform extends PlatformInterface {
  /// Constructs a FlutterLogrocketPlatform.
  FlutterLogrocketPlatform() : super(token: _token);

  static final Object _token = Object();

  static FlutterLogrocketPlatform _instance = MethodChannelFlutterLogrocket();

  /// The default instance of [FlutterLogrocketPlatform] to use.
  ///
  /// Defaults to [MethodChannelFlutterLogrocket].
  static FlutterLogrocketPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [FlutterLogrocketPlatform] when
  /// they register themselves.
  static set instance(FlutterLogrocketPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  void initLogrocket(String appId) {
    throw UnimplementedError('Logrocket has not been implemented.');
  }

  void identifyUser(
    String userId,
    String? name,
    String? email,
  ) {
    throw UnimplementedError('identifyUser has not been implemented.');
  }
}
