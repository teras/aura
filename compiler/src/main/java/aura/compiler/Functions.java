/*
 * Copyright (C) 2012 RoboVM AB
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl-2.0.html>.
 */
package aura.compiler;

import java.util.List;

import aura.compiler.llvm.Alloca;
import aura.compiler.llvm.Argument;
import aura.compiler.llvm.BasicBlockRef;
import aura.compiler.llvm.Call;
import aura.compiler.llvm.Function;
import aura.compiler.llvm.FunctionRef;
import aura.compiler.llvm.FunctionType;
import aura.compiler.llvm.Getelementptr;
import aura.compiler.llvm.IntegerConstant;
import aura.compiler.llvm.Invoke;
import aura.compiler.llvm.PointerType;
import aura.compiler.llvm.Store;
import aura.compiler.llvm.Switch;
import aura.compiler.llvm.TailCall;
import aura.compiler.llvm.Type;
import aura.compiler.llvm.Value;
import aura.compiler.llvm.Variable;

/**
 * @author niklas
 *
 */
public class Functions {

    public static final FunctionRef BC_INITIALIZE_CLASS = new FunctionRef("_bcInitializeClass", new FunctionType(Type.VOID, Types.ENV_PTR, Type.I8_PTR_PTR));
    public static final FunctionRef BC_ALLOCATE = new FunctionRef("_bcAllocate", new FunctionType(Types.OBJECT_PTR, Types.ENV_PTR, Type.I8_PTR_PTR));
    public static final FunctionRef BC_LDC_ARRAY_BOOT_CLASS = new FunctionRef("_bcLdcArrayBootClass", new FunctionType(Types.OBJECT_PTR, Types.ENV_PTR, new PointerType(Types.OBJECT_PTR), Type.I8_PTR));
    public static final FunctionRef BC_LDC_ARRAY_CLASS = new FunctionRef("_bcLdcArrayClass", new FunctionType(Types.OBJECT_PTR, Types.ENV_PTR, new PointerType(Types.OBJECT_PTR), Type.I8_PTR));
    public static final FunctionRef BC_NEW_OBJECT_ARRAY = new FunctionRef("_bcNewObjectArray", new FunctionType(Types.OBJECT_PTR, Types.ENV_PTR, Type.I32, Types.OBJECT_PTR));
    public static final FunctionRef BC_LDC_CLASS = new FunctionRef("_bcLdcClass", new FunctionType(Types.OBJECT_PTR, Types.ENV_PTR, Type.I8_PTR_PTR));

    public static final FunctionRef BC_EXCEPTION_CLEAR = new FunctionRef("_bcExceptionClear", new FunctionType(Types.OBJECT_PTR, Types.ENV_PTR));
    public static final FunctionRef BC_THROW = new FunctionRef("_bcThrow", new FunctionType(Type.VOID, Types.ENV_PTR, Types.OBJECT_PTR));
    public static final FunctionRef BC_THROW_IF_EXCEPTION_OCCURRED = new FunctionRef("_bcThrowIfExceptionOccurred", new FunctionType(Type.VOID, Types.ENV_PTR));
    public static final FunctionRef BC_THROW_UNSATISIFED_LINK_ERROR = new FunctionRef("_bcThrowUnsatisfiedLinkError", new FunctionType(Type.VOID, Types.ENV_PTR, Type.I8_PTR));
    public static final FunctionRef BC_THROW_UNSATISIFED_LINK_ERROR_BRIDGE_NOT_BOUND = new FunctionRef("_bcThrowUnsatisfiedLinkErrorBridgeNotBound", new FunctionType(Type.VOID, Types.ENV_PTR, Type.I8_PTR, Type.I8_PTR, Type.I8_PTR));
    public static final FunctionRef BC_THROW_UNSATISIFED_LINK_ERROR_OPTIONAL_BRIDGE_NOT_BOUND = new FunctionRef("_bcThrowUnsatisfiedLinkErrorOptionalBridgeNotBound", new FunctionType(Type.VOID, Types.ENV_PTR, Type.I8_PTR, Type.I8_PTR, Type.I8_PTR));
    public static final FunctionRef BC_THROW_NO_CLASS_DEF_FOUND_ERROR = new FunctionRef("_bcThrowNoClassDefFoundError", new FunctionType(Type.VOID, Types.ENV_PTR, Type.I8_PTR));
    public static final FunctionRef BC_THROW_NO_SUCH_FIELD_ERROR = new FunctionRef("_bcThrowNoSuchFieldError", new FunctionType(Type.VOID, Types.ENV_PTR, Type.I8_PTR));
    public static final FunctionRef BC_THROW_NO_SUCH_METHOD_ERROR = new FunctionRef("_bcThrowNoSuchMethodError", new FunctionType(Type.VOID, Types.ENV_PTR, Type.I8_PTR));
    public static final FunctionRef BC_THROW_ILLEGAL_ACCESS_ERROR = new FunctionRef("_bcThrowIllegalAccessError", new FunctionType(Type.VOID, Types.ENV_PTR, Type.I8_PTR));
    public static final FunctionRef BC_THROW_INSTANTIATION_ERROR = new FunctionRef("_bcThrowInstantiationError", new FunctionType(Type.VOID, Types.ENV_PTR, Type.I8_PTR));
    public static final FunctionRef BC_THROW_INCOMPATIBLE_CLASS_CHANGE_ERROR = new FunctionRef("_bcThrowIncompatibleClassChangeError", new FunctionType(Type.VOID, Types.ENV_PTR, Type.I8_PTR));
    public static final FunctionRef BC_THROW_ABSTRACT_METHOD_ERROR = new FunctionRef("_bcThrowAbstractMethodError", new FunctionType(Type.VOID, Types.ENV_PTR, Type.I8_PTR));
    public static final FunctionRef BC_THROW_CLASS_CAST_EXCEPTION_ARRAY = new FunctionRef("_bcThrowClassCastExceptionArray", new FunctionType(Type.VOID, Types.ENV_PTR, Types.CLASS_PTR, Types.OBJECT_PTR));
    
    public static final FunctionRef BC_NEW_BOOLEAN_ARRAY = new FunctionRef("_bcNewBooleanArray", new FunctionType(Types.OBJECT_PTR, Types.ENV_PTR, Type.I32));
    public static final FunctionRef BC_NEW_BYTE_ARRAY = new FunctionRef("_bcNewByteArray", new FunctionType(Types.OBJECT_PTR, Types.ENV_PTR, Type.I32));
    public static final FunctionRef BC_NEW_CHAR_ARRAY = new FunctionRef("_bcNewCharArray", new FunctionType(Types.OBJECT_PTR, Types.ENV_PTR, Type.I32));
    public static final FunctionRef BC_NEW_SHORT_ARRAY = new FunctionRef("_bcNewShortArray", new FunctionType(Types.OBJECT_PTR, Types.ENV_PTR, Type.I32));
    public static final FunctionRef BC_NEW_INT_ARRAY = new FunctionRef("_bcNewIntArray", new FunctionType(Types.OBJECT_PTR, Types.ENV_PTR, Type.I32));
    public static final FunctionRef BC_NEW_LONG_ARRAY = new FunctionRef("_bcNewLongArray", new FunctionType(Types.OBJECT_PTR, Types.ENV_PTR, Type.I32));
    public static final FunctionRef BC_NEW_FLOAT_ARRAY = new FunctionRef("_bcNewFloatArray", new FunctionType(Types.OBJECT_PTR, Types.ENV_PTR, Type.I32));
    public static final FunctionRef BC_NEW_DOUBLE_ARRAY = new FunctionRef("_bcNewDoubleArray", new FunctionType(Types.OBJECT_PTR, Types.ENV_PTR, Type.I32));
    public static final FunctionRef BC_MONITOR_ENTER = new FunctionRef("_bcMonitorEnter", new FunctionType(Type.VOID, Types.ENV_PTR, Types.OBJECT_PTR));
    public static final FunctionRef BC_MONITOR_EXIT = new FunctionRef("_bcMonitorExit", new FunctionType(Type.VOID, Types.ENV_PTR, Types.OBJECT_PTR));
    public static final FunctionRef BC_LDC_STRING = new FunctionRef("_bcLdcString", new FunctionType(Types.OBJECT_PTR, Types.ENV_PTR, Type.I8_PTR_PTR, Type.I8_PTR));
    public static final FunctionRef BC_LOOKUP_VIRTUAL_METHOD = new FunctionRef("_bcLookupVirtualMethod", new FunctionType(Type.I8_PTR, Types.ENV_PTR, Types.OBJECT_PTR, Type.I8_PTR, Type.I8_PTR));
    public static final FunctionRef BC_LOOKUP_INTERFACE_METHOD = new FunctionRef("_bcLookupInterfaceMethod", new FunctionType(Type.I8_PTR, Types.ENV_PTR, Type.I8_PTR_PTR, Types.OBJECT_PTR, Type.I8_PTR, Type.I8_PTR));
    public static final FunctionRef BC_LOOKUP_INTERFACE_METHOD_IMPL = new FunctionRef("_bcLookupInterfaceMethodImpl", new FunctionType(Type.I8_PTR, Types.ENV_PTR, Type.I8_PTR_PTR, Types.OBJECT_PTR, Type.I32));
    public static final FunctionRef BC_CHECKCAST = new FunctionRef("_bcCheckcast", new FunctionType(Types.OBJECT_PTR, Types.ENV_PTR, Type.I8_PTR_PTR, Types.OBJECT_PTR));
    public static final FunctionRef BC_CHECKCAST_ARRAY = new FunctionRef("_bcCheckcastArray", new FunctionType(Types.OBJECT_PTR, Types.ENV_PTR, Types.OBJECT_PTR, Types.OBJECT_PTR));
    public static final FunctionRef BC_INSTANCEOF = new FunctionRef("_bcInstanceof", new FunctionType(Type.I32, Types.ENV_PTR, Type.I8_PTR_PTR, Types.OBJECT_PTR));
    public static final FunctionRef BC_INSTANCEOF_ARRAY = new FunctionRef("_bcInstanceofArray", new FunctionType(Type.I32, Types.ENV_PTR, Types.OBJECT_PTR, Types.OBJECT_PTR));
    public static final FunctionRef BC_NEW_MULTI_ARRAY = new FunctionRef("_bcNewMultiArray", new FunctionType(Types.OBJECT_PTR, Types.ENV_PTR, Type.I32, new PointerType(Type.I32), Types.OBJECT_PTR));
    public static final FunctionRef BC_SET_OBJECT_ARRAY_ELEMENT = new FunctionRef("_bcSetObjectArrayElement", new FunctionType(Type.VOID, Types.ENV_PTR, Types.OBJECT_PTR, Type.I32, Types.OBJECT_PTR));
    public static final FunctionRef BC_RESOLVE_NATIVE = new FunctionRef("_bcResolveNative", new FunctionType(Type.I8_PTR, Types.ENV_PTR, Types.OBJECT_PTR, Type.I8_PTR, Type.I8_PTR, Type.I8_PTR, Type.I8_PTR, Type.I8_PTR, Type.I8_PTR));
    public static final FunctionRef BC_PUSH_NATIVE_FRAME = new FunctionRef("_bcPushNativeFrame", new FunctionType(Type.VOID, Types.ENV_PTR, Types.GATEWAY_FRAME_PTR, Type.I8_PTR));
    public static final FunctionRef BC_POP_NATIVE_FRAME = new FunctionRef("_bcPopNativeFrame", new FunctionType(Type.VOID, Types.ENV_PTR));
    public static final FunctionRef BC_PUSH_CALLBACK_FRAME = new FunctionRef("_bcPushCallbackFrame", new FunctionType(Type.VOID, Types.ENV_PTR, Types.GATEWAY_FRAME_PTR, Type.I8_PTR));
    public static final FunctionRef BC_POP_CALLBACK_FRAME = new FunctionRef("_bcPopCallbackFrame", new FunctionType(Type.VOID, Types.ENV_PTR));
    public static final FunctionRef BC_ATTACH_THREAD_FROM_CALLBACK = new FunctionRef("_bcAttachThreadFromCallback", new FunctionType(Types.ENV_PTR));
    public static final FunctionRef BC_DETACH_THREAD_FROM_CALLBACK = new FunctionRef("_bcDetachThreadFromCallback", new FunctionType(Type.VOID, Types.ENV_PTR));
    public static final FunctionRef RVM_TRYCATCH_ENTER = new FunctionRef("rvmTrycatchEnter", new FunctionType(Type.I32, Types.ENV_PTR, Types.TRYCATCH_CONTEXT_PTR));
    public static final FunctionRef BC_TRYCATCH_LEAVE = new FunctionRef("_bcTrycatchLeave", new FunctionType(Type.VOID, Types.ENV_PTR));
    public static final FunctionRef BC_ABSTRACT_METHOD_CALLED = new FunctionRef("_bcAbstractMethodCalled", new FunctionType(Type.VOID, Types.ENV_PTR, Types.OBJECT_PTR));
    public static final FunctionRef BC_NON_PUBLIC_METHOD_CALLED = new FunctionRef("_bcNonPublicMethodCalled", new FunctionType(Type.VOID, Types.ENV_PTR, Types.OBJECT_PTR));
    public static final FunctionRef BC_COPY_STRUCT = new FunctionRef("_bcCopyStruct", new FunctionType(Type.I8_PTR, Types.ENV_PTR, Type.I8_PTR, Type.I32));
    public static final FunctionRef BC_HOOK_INSTRUMENTED = new FunctionRef("_bcHookInstrumented", new FunctionType(Type.VOID, Types.ENV_PTR, Type.I32, Type.I32, Type.I8_PTR, Type.I8_PTR));

    public static final FunctionRef LLVM_FRAMEADDRESS = new FunctionRef("llvm.frameaddress", new FunctionType(Type.I8_PTR, Type.I32));
    public static final FunctionRef LLVM_MEMCPY = new FunctionRef("llvm.memcpy.p0i8.p0i8.i32", new FunctionType(Type.VOID, Type.I8_PTR, Type.I8_PTR, Type.I32, Type.I32, Type.I1));

    public static final FunctionRef REGISTER_FINALIZABLE = new FunctionRef("register_finalizable", new FunctionType(Type.VOID, Types.ENV_PTR, Types.OBJECT_PTR));
    public static final FunctionRef CHECK_NULL = new FunctionRef("checknull", new FunctionType(Type.I8, Types.ENV_PTR, Types.OBJECT_PTR));
    public static final FunctionRef CHECK_NULL_I8_PTR = new FunctionRef("checknull_i8_ptr", new FunctionType(Type.I8, Types.ENV_PTR, Type.I8_PTR));
    public static final FunctionRef CHECK_LOWER = new FunctionRef("checklower", new FunctionType(Type.VOID, Types.ENV_PTR, Types.OBJECT_PTR, Type.I32));
    public static final FunctionRef CHECK_UPPER = new FunctionRef("checkupper", new FunctionType(Type.VOID, Types.ENV_PTR, Types.OBJECT_PTR, Type.I32));
    public static final FunctionRef CHECK_STACK_OVERFLOW = new FunctionRef("checkso", new FunctionType(Type.VOID));
    public static final FunctionRef ARRAY_LENGTH = new FunctionRef("arraylength", new FunctionType(Type.I32, Types.OBJECT_PTR));
    public static final FunctionRef BALOAD = new FunctionRef("baload", new FunctionType(Type.I8, Types.OBJECT_PTR, Type.I32));
    public static final FunctionRef SALOAD = new FunctionRef("saload", new FunctionType(Type.I16, Types.OBJECT_PTR, Type.I32));
    public static final FunctionRef CALOAD = new FunctionRef("caload", new FunctionType(Type.I16, Types.OBJECT_PTR, Type.I32));
    public static final FunctionRef IALOAD = new FunctionRef("iaload", new FunctionType(Type.I32, Types.OBJECT_PTR, Type.I32));
    public static final FunctionRef LALOAD = new FunctionRef("laload", new FunctionType(Type.I64, Types.OBJECT_PTR, Type.I32));
    public static final FunctionRef FALOAD = new FunctionRef("faload", new FunctionType(Type.FLOAT, Types.OBJECT_PTR, Type.I32));
    public static final FunctionRef DALOAD = new FunctionRef("daload", new FunctionType(Type.DOUBLE, Types.OBJECT_PTR, Type.I32));
    public static final FunctionRef AALOAD = new FunctionRef("aaload", new FunctionType(Types.OBJECT_PTR, Types.OBJECT_PTR, Type.I32));
    public static final FunctionRef BASTORE = new FunctionRef("bastore", new FunctionType(Type.VOID, Types.OBJECT_PTR, Type.I32, Type.I8));
    public static final FunctionRef SASTORE = new FunctionRef("sastore", new FunctionType(Type.VOID, Types.OBJECT_PTR, Type.I32, Type.I16));
    public static final FunctionRef CASTORE = new FunctionRef("castore", new FunctionType(Type.VOID, Types.OBJECT_PTR, Type.I32, Type.I16));
    public static final FunctionRef IASTORE = new FunctionRef("iastore", new FunctionType(Type.VOID, Types.OBJECT_PTR, Type.I32, Type.I32));
    public static final FunctionRef LASTORE = new FunctionRef("lastore", new FunctionType(Type.VOID, Types.OBJECT_PTR, Type.I32, Type.I64));
    public static final FunctionRef FASTORE = new FunctionRef("fastore", new FunctionType(Type.VOID, Types.OBJECT_PTR, Type.I32, Type.FLOAT));
    public static final FunctionRef DASTORE = new FunctionRef("dastore", new FunctionType(Type.VOID, Types.OBJECT_PTR, Type.I32, Type.DOUBLE));
    public static final FunctionRef AASTORE = new FunctionRef("aastore", new FunctionType(Type.VOID, Types.OBJECT_PTR, Type.I32, Types.OBJECT_PTR));
    public static final FunctionRef F2I = new FunctionRef("f2i", new FunctionType(Type.I32, Type.FLOAT));
    public static final FunctionRef F2L = new FunctionRef("f2l", new FunctionType(Type.I64, Type.FLOAT));
    public static final FunctionRef D2I = new FunctionRef("d2i", new FunctionType(Type.I32, Type.DOUBLE));
    public static final FunctionRef D2L = new FunctionRef("d2l", new FunctionType(Type.I64, Type.DOUBLE));
    public static final FunctionRef IDIV = new FunctionRef("idiv", new FunctionType(Type.I32, Types.ENV_PTR, Type.I32, Type.I32));
    public static final FunctionRef LDIV = new FunctionRef("ldiv", new FunctionType(Type.I64, Types.ENV_PTR, Type.I64, Type.I64));
    public static final FunctionRef IREM = new FunctionRef("irem", new FunctionType(Type.I32, Types.ENV_PTR, Type.I32, Type.I32));
    public static final FunctionRef LREM = new FunctionRef("lrem", new FunctionType(Type.I64, Types.ENV_PTR, Type.I64, Type.I64));
    public static final FunctionRef FREM = new FunctionRef("frem", new FunctionType(Type.FLOAT, Types.ENV_PTR, Type.FLOAT, Type.FLOAT));
    public static final FunctionRef DREM = new FunctionRef("drem", new FunctionType(Type.DOUBLE, Types.ENV_PTR, Type.DOUBLE, Type.DOUBLE));
    public static final FunctionRef FCMPL = new FunctionRef("fcmpl", new FunctionType(Type.I32, Type.FLOAT, Type.FLOAT));
    public static final FunctionRef FCMPG = new FunctionRef("fcmpg", new FunctionType(Type.I32, Type.FLOAT, Type.FLOAT));
    public static final FunctionRef DCMPL = new FunctionRef("dcmpl", new FunctionType(Type.I32, Type.DOUBLE, Type.DOUBLE));
    public static final FunctionRef DCMPG = new FunctionRef("dcmpg", new FunctionType(Type.I32, Type.DOUBLE, Type.DOUBLE));
    public static final FunctionRef LDC_CLASS = new FunctionRef("ldcClass", new FunctionType(Types.OBJECT_PTR, Types.ENV_PTR, Type.I8_PTR_PTR));
    public static final FunctionRef LDC_CLASS_WRAPPER = new FunctionRef("ldcClassWrapper", new FunctionType(Types.OBJECT_PTR, Types.ENV_PTR, Type.I8_PTR_PTR));
    public static final FunctionRef CHECKCAST_WRAPPER = new FunctionRef("checkcastWrapper", new FunctionType(Types.OBJECT_PTR, Types.ENV_PTR, Type.I8_PTR_PTR, Types.OBJECT_PTR));
    public static final FunctionRef INSTANCEOF_WRAPPER = new FunctionRef("instanceofWrapper", new FunctionType(Type.I32, Types.ENV_PTR, Type.I8_PTR_PTR, Types.OBJECT_PTR));
    public static final FunctionRef CHECKCAST_CLASS = new FunctionRef("checkcast_class", new FunctionType(Types.OBJECT_PTR, Types.ENV_PTR, Type.I8_PTR_PTR, Types.OBJECT_PTR, Type.I32, Type.I32));
    public static final FunctionRef CHECKCAST_INTERFACE = new FunctionRef("checkcast_interface", new FunctionType(Types.OBJECT_PTR, Types.ENV_PTR, Type.I8_PTR_PTR, Types.OBJECT_PTR, Type.I32));
    public static final FunctionRef CHECKCAST_PRIM_ARRAY = new FunctionRef("checkcast_prim_array", new FunctionType(Types.OBJECT_PTR, Types.ENV_PTR, Types.CLASS_PTR, Types.OBJECT_PTR));
    public static final FunctionRef INSTANCEOF_CLASS = new FunctionRef("instanceof_class", new FunctionType(Type.I32, Types.ENV_PTR, Type.I8_PTR_PTR, Types.OBJECT_PTR, Type.I32, Type.I32));
    public static final FunctionRef INSTANCEOF_INTERFACE = new FunctionRef("instanceof_interface", new FunctionType(Type.I32, Types.ENV_PTR, Type.I8_PTR_PTR, Types.OBJECT_PTR, Type.I32));
    public static final FunctionRef INSTANCEOF_PRIM_ARRAY = new FunctionRef("instanceof_prim_array", new FunctionType(Type.I32, Types.ENV_PTR, Types.CLASS_PTR, Types.OBJECT_PTR));
    public static final FunctionRef OBJECT_CLASS = new FunctionRef("Object_class", new FunctionType(Types.CLASS_PTR, Types.OBJECT_PTR));
    public static final FunctionRef CLASS_VITABLE = new FunctionRef("Class_vitable", new FunctionType(Types.VITABLE_PTR, Types.CLASS_PTR));
    public static final FunctionRef MONITORENTER = new FunctionRef("monitorenter", new FunctionType(Type.VOID, Types.ENV_PTR, Types.OBJECT_PTR));
    public static final FunctionRef MONITOREXIT = new FunctionRef("monitorexit", new FunctionType(Type.VOID, Types.ENV_PTR, Types.OBJECT_PTR));
    public static final FunctionRef PUSH_NATIVE_FRAME = new FunctionRef("pushNativeFrame", new FunctionType(Type.VOID, Types.ENV_PTR));
    public static final FunctionRef POP_NATIVE_FRAME = new FunctionRef("popNativeFrame", new FunctionType(Type.VOID, Types.ENV_PTR));
    public static final FunctionRef GETPC = new FunctionRef("getpc", new FunctionType(Type.I8_PTR));
	/* Merged from https://github.com/MobiDevelop/robovm */
    public static final FunctionRef PUSH_SHADOW_FRAME = new FunctionRef("rvmPushShadowFrame", new FunctionType(Type.VOID, Types.ENV_PTR, Type.I8_PTR, Type.I8_PTR));
    public static final FunctionRef POP_SHADOW_FRAME = new FunctionRef("rvmPopShadowFrame", new FunctionType(Type.VOID, Types.ENV_PTR));
    public static final FunctionRef PUSH_SHADOW_LINE_NUMBER = new FunctionRef("rvmPushShadowFrameLineNumber", new FunctionType(Type.VOID, Types.ENV_PTR, Type.I32));
	/* end merge */


    public static FunctionRef getArrayLoad(soot.Type sootType) {
        if (sootType.equals(soot.BooleanType.v())) {
            return BALOAD;
        } else if (sootType.equals(soot.ByteType.v())) {
            return BALOAD;
        } else if (sootType.equals(soot.ShortType.v())) {
            return SALOAD;
        } else if (sootType.equals(soot.CharType.v())) {
            return CALOAD;
        } else if (sootType.equals(soot.IntType.v())) {
            return IALOAD;
        } else if (sootType.equals(soot.LongType.v())) {
            return LALOAD;
        } else if (sootType.equals(soot.FloatType.v())) {
            return FALOAD;
        } else if (sootType.equals(soot.DoubleType.v())) {
            return DALOAD;
        } else if (sootType instanceof soot.RefLikeType) {
            return AALOAD;
        } else {
            throw new IllegalArgumentException("Unknown Type: " + sootType);
        }
    }
    
    public static FunctionRef getArrayStore(soot.Type sootType) {
        if (sootType.equals(soot.BooleanType.v())) {
            return BASTORE;
        } else if (sootType.equals(soot.ByteType.v())) {
            return BASTORE;
        } else if (sootType.equals(soot.ShortType.v())) {
            return SASTORE;
        } else if (sootType.equals(soot.CharType.v())) {
            return CASTORE;
        } else if (sootType.equals(soot.IntType.v())) {
            return IASTORE;
        } else if (sootType.equals(soot.LongType.v())) {
            return LASTORE;
        } else if (sootType.equals(soot.FloatType.v())) {
            return FASTORE;
        } else if (sootType.equals(soot.DoubleType.v())) {
            return DASTORE;
        } else if (sootType instanceof soot.RefLikeType) {
            return AASTORE;
        } else {
            throw new IllegalArgumentException("Unknown Type: " + sootType);
        }
    }
    
    public static FunctionRef getNewArray(soot.Type sootType) {
        if (sootType.equals(soot.BooleanType.v())) {
            return BC_NEW_BOOLEAN_ARRAY;
        } else if (sootType.equals(soot.ByteType.v())) {
            return BC_NEW_BYTE_ARRAY;
        } else if (sootType.equals(soot.ShortType.v())) {
            return BC_NEW_SHORT_ARRAY;
        } else if (sootType.equals(soot.CharType.v())) {
            return BC_NEW_CHAR_ARRAY;
        } else if (sootType.equals(soot.IntType.v())) {
            return BC_NEW_INT_ARRAY;
        } else if (sootType.equals(soot.LongType.v())) {
            return BC_NEW_LONG_ARRAY;
        } else if (sootType.equals(soot.FloatType.v())) {
            return BC_NEW_FLOAT_ARRAY;
        } else if (sootType.equals(soot.DoubleType.v())) {
            return BC_NEW_DOUBLE_ARRAY;
        } else {
            throw new IllegalArgumentException("Unknown Type: " + sootType);
        }
    }

    public static Value call(Function currentFunction, Value fn, List<Value> args) {
        return call(currentFunction, fn, args.toArray(new Value[args.size()]));
    }
    
    public static Value call(Function currentFunction, Value fn, Value ... args) {
        Variable result = null;
        Type returnType = ((FunctionType) fn.getType()).getReturnType();
        if (returnType != Type.VOID) {
            result = currentFunction.newVariable(returnType);
        }
        currentFunction.add(new Call(result, fn, args));
        return result == null ? null : result.ref();
    }
    
    public static Value tailcall(Function currentFunction, Value fn, Value ... args) {
        Variable result = null;
        Type returnType = ((FunctionType) fn.getType()).getReturnType();
        if (returnType != Type.VOID) {
            result = currentFunction.newVariable(returnType);
        }
        currentFunction.add(new TailCall(result, fn, args));
        return result == null ? null : result.ref();
    }
    
    public static Value callWithArguments(Function currentFunction, Value fn, List<Argument> args) {
        return callWithArguments(currentFunction, fn, args.toArray(new Argument[args.size()]));
    }
    
    public static Value callWithArguments(Function currentFunction, Value fn, Argument ... args) {
        Variable result = null;
        Type returnType = ((FunctionType) fn.getType()).getReturnType();
        if (returnType != Type.VOID) {
            result = currentFunction.newVariable(returnType);
        }
        currentFunction.add(new Call(result, fn, args));
        return result == null ? null : result.ref();
    }
    
    public static Value invoke(Function currentFunction, Value fn, 
            BasicBlockRef success, BasicBlockRef failure, List<Value> args) {
        
        return invoke(currentFunction, fn, success, failure, args.toArray(new Value[args.size()]));
    }
    
    public static Value invoke(Function currentFunction, Value fn, 
            BasicBlockRef success, BasicBlockRef failure, Value ... args) {
        
        Variable result = null;
        Type returnType = ((FunctionType) fn.getType()).getReturnType();
        if (returnType != Type.VOID) {
            result = currentFunction.newVariable(returnType);
        }
        currentFunction.add(new Invoke(result, fn, success, failure, args));
        return result == null ? null : result.ref();
    }
    
    public static void pushNativeFrame(Function fn) {
        call(fn, PUSH_NATIVE_FRAME, fn.getParameterRef(0));
    }

    public static void popNativeFrame(Function fn) {
        call(fn, POP_NATIVE_FRAME, fn.getParameterRef(0));
    }
    
    public static void pushCallbackFrame(Function fn, Value env) {
        Variable gwFrame = fn.newVariable(Types.GATEWAY_FRAME_PTR);
        fn.add(new Alloca(gwFrame, Types.GATEWAY_FRAME));
        Value frameAddress = call(fn, LLVM_FRAMEADDRESS, new IntegerConstant(0));
        call(fn, BC_PUSH_CALLBACK_FRAME, env, gwFrame.ref(), frameAddress);
    }

    public static void popCallbackFrame(Function fn, Value env) {
        call(fn, BC_POP_CALLBACK_FRAME, env);
    }
    
    public static void trycatchAllEnter(Function fn, BasicBlockRef onNoException, BasicBlockRef onException) {
        trycatchAllEnter(fn, fn.getParameterRef(0), onNoException, onException);
    }

    public static void trycatchAllEnter(Function fn, Value env, BasicBlockRef onNoException, BasicBlockRef onException) {
        Variable ctx = fn.newVariable(Types.TRYCATCH_CONTEXT_PTR);
        fn.add(new Alloca(ctx, Types.TRYCATCH_CONTEXT));
        Variable selPtr = fn.newVariable(new PointerType(Type.I32));
        fn.add(new Getelementptr(selPtr, ctx.ref(), 0, 1));
        fn.add(new Store(new IntegerConstant(-1), selPtr.ref()));        
        Value result = call(fn, RVM_TRYCATCH_ENTER, env, ctx.ref());
        fn.add(new Switch(result, onException, new IntegerConstant(0), onNoException));        
    }
    
    public static void trycatchLeave(Function fn) {
        trycatchLeave(fn, fn.getParameterRef(0));
    }
    
    public static void trycatchLeave(Function fn, Value env) {
        call(fn, BC_TRYCATCH_LEAVE, env);
    }
}
