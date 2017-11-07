package ru.mipt.java2017.hw2;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.7.0)",
    comments = "Source: primaryNumberFinder.proto")
public final class SenderGrpc {

  private SenderGrpc() {}

  public static final String SERVICE_NAME = "ru.mipt.java2017.sm06.Sender";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<ru.mipt.java2017.hw2.Request,
      ru.mipt.java2017.hw2.Reply> METHOD_SEND =
      io.grpc.MethodDescriptor.<ru.mipt.java2017.hw2.Request, ru.mipt.java2017.hw2.Reply>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "ru.mipt.java2017.sm06.Sender", "Send"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              ru.mipt.java2017.hw2.Request.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              ru.mipt.java2017.hw2.Reply.getDefaultInstance()))
          .setSchemaDescriptor(new SenderMethodDescriptorSupplier("Send"))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SenderStub newStub(io.grpc.Channel channel) {
    return new SenderStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SenderBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new SenderBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SenderFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new SenderFutureStub(channel);
  }

  /**
   */
  public static abstract class SenderImplBase implements io.grpc.BindableService {

    /**
     */
    public void send(ru.mipt.java2017.hw2.Request request,
        io.grpc.stub.StreamObserver<ru.mipt.java2017.hw2.Reply> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_SEND, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_SEND,
            asyncUnaryCall(
              new MethodHandlers<
                ru.mipt.java2017.hw2.Request,
                ru.mipt.java2017.hw2.Reply>(
                  this, METHODID_SEND)))
          .build();
    }
  }

  /**
   */
  public static final class SenderStub extends io.grpc.stub.AbstractStub<SenderStub> {
    private SenderStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SenderStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SenderStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SenderStub(channel, callOptions);
    }

    /**
     */
    public void send(ru.mipt.java2017.hw2.Request request,
        io.grpc.stub.StreamObserver<ru.mipt.java2017.hw2.Reply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_SEND, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class SenderBlockingStub extends io.grpc.stub.AbstractStub<SenderBlockingStub> {
    private SenderBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SenderBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SenderBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SenderBlockingStub(channel, callOptions);
    }

    /**
     */
    public ru.mipt.java2017.hw2.Reply send(ru.mipt.java2017.hw2.Request request) {
      return blockingUnaryCall(
          getChannel(), METHOD_SEND, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class SenderFutureStub extends io.grpc.stub.AbstractStub<SenderFutureStub> {
    private SenderFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SenderFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SenderFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SenderFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ru.mipt.java2017.hw2.Reply> send(
        ru.mipt.java2017.hw2.Request request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_SEND, getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SenderImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SenderImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND:
          serviceImpl.send((ru.mipt.java2017.hw2.Request) request,
              (io.grpc.stub.StreamObserver<ru.mipt.java2017.hw2.Reply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class SenderBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SenderBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ru.mipt.java2017.hw2.PrimaryNumberFinder.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Sender");
    }
  }

  private static final class SenderFileDescriptorSupplier
      extends SenderBaseDescriptorSupplier {
    SenderFileDescriptorSupplier() {}
  }

  private static final class SenderMethodDescriptorSupplier
      extends SenderBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SenderMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SenderGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SenderFileDescriptorSupplier())
              .addMethod(METHOD_SEND)
              .build();
        }
      }
    }
    return result;
  }
}
