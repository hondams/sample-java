package com.github.hondams.fw.validation;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

import org.hibernate.validator.internal.engine.MessageInterpolatorContext;

import jakarta.validation.ElementKind;
import jakarta.validation.Path;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageInterpolatorUtils {

    public Object getValidationTarget(MessageInterpolatorContext context) {
        Class<?> rootBeanType = context.getRootBeanType();
        List<Path.Node> pathNodes = getPathNodes(context);

        // remove root bean node
        pathNodes.remove(0);

        if (pathNodes.isEmpty()) {
            return rootBeanType;
        }

        // TODO:プロパティパスのNodeの構成については、不明なので、必要に応じて実装を見直す。
	
        // org.springframework.validation.beanvalidation.MethodValidationAdapter.adaptViolations
        // org.hibernate.validator.internal.engine.ValidatorImpl.getValueContextForPropertyValidation

        return getValidationTarget(context, rootBeanType, pathNodes, 1);
    }

    private Object getValidationTarget( // NOSONAR
        MessageInterpolatorContext context, Class<?> beanType, List<Path.Node> pathNodes, int index) {

        Path.Node node = pathNodes.remove(0);

        switch (node.getKind()) {
            case CONSTRUCTOR -> {
                if (node instanceof Path.ConstructorNode constructorNode) {// NOSONAR
                    Constructor<?> constructor;
                    try {
                        constructor = beanType
                                .getConstructor(constructorNode.getParameterTypes().toArray(new Class<?>[0]));
                        if (pathNodes.isEmpty()) {
                            return constructor;
                        }
                        return getValidationTarget(context, constructor, pathNodes, index + 1);
                    } catch (ReflectiveOperationException e) {
                        throw new IllegalStateException("Unexpected property path: propertyPath="
                                + getDebugPropertyPath(context) + ", index=" + index, e);
                    }
                }
            }
            case METHOD -> {
                if (node instanceof Path.MethodNode methodNode) {// NOSONAR
                    try {
                        Method method = beanType.getMethod(methodNode.getName(),
                                methodNode.getParameterTypes().toArray(new Class<?>[0]));
                        if (pathNodes.isEmpty()) {
                            return method;
                        }
                        return getValidationTarget(context, method, pathNodes, index + 1);
                    } catch (ReflectiveOperationException e) {
                        throw new IllegalStateException("Unexpected property path: propertyPath="
                                + getDebugPropertyPath(context) + ", index=" + index, e);
                    }
                }
            }
            case PROPERTY -> {
                if (node instanceof Path.PropertyNode propertyNode) {// NOSONAR
                    try {
                        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyNode.getName(),
                                beanType);
                        if (pathNodes.isEmpty()) {
                            return propertyDescriptor;
                        }
                    } catch (IntrospectionException e) {
                        throw new IllegalStateException("Unexpected property path: propertyPath="
                                + getDebugPropertyPath(context) + ", index=" + index, e);
                    }
                }
            }
            default -> {
                // do nothing
            }
        }
        throw new IllegalStateException(
                "Unexpected property path: propertyPath=" + getDebugPropertyPath(context) + ", index=" + index);
    }

    private Object getValidationTarget(MessageInterpolatorContext context, Constructor<?> constructor,
            List<Path.Node> pathNodes, int index) {

        Path.Node node = pathNodes.remove(0);

        switch (node.getKind()) { // NOSONAR
            case PARAMETER -> {
                if (node instanceof Path.ParameterNode parameterNode) {// NOSONAR
                    Parameter parameter = constructor.getParameters()[parameterNode.getParameterIndex()];
                    if (pathNodes.isEmpty()) {
                        return parameter;
                    }
                }
            }
            default -> {
                // do nothing
            }
        }
        throw new IllegalStateException(
                "Unexpected property path: propertyPath=" + getDebugPropertyPath(context) + ", index=" + index);
    }

    private Object getValidationTarget(MessageInterpolatorContext context, Method method, List<Path.Node> pathNodes,
            int index) {

        Path.Node node = pathNodes.remove(0);

        switch (node.getKind()) {
            case PARAMETER -> {
                if (node instanceof Path.ParameterNode parameterNode) {// NOSONAR
                    Parameter parameter = method.getParameters()[parameterNode.getParameterIndex()];
                    if (pathNodes.isEmpty()) {
                        return parameter;
                    }
                }
            }
            case RETURN_VALUE -> {
                if (pathNodes.isEmpty()) {// NOSONAR
                    return method;
                }
            }
            default -> {
                // do nothing
            }
        }
        throw new IllegalStateException(
                "Unexpected property path: propertyPath=" + getDebugPropertyPath(context) + ", index=" + index);
    }


    private Object getValidationTarget(MessageInterpolatorContext context, PropertyDescriptor propertyDescriptor, List<Path.Node> pathNodes,
            int index) {

        Path.Node node = pathNodes.remove(0);

        switch (node.getKind()) {
            case CONTAINER_ELEMENT -> {
                if (node instanceof Path.ParameterNode parameterNode) {// NOSONAR
                    Parameter parameter = method.getParameters()[parameterNode.getParameterIndex()];
                    if (pathNodes.isEmpty()) {
                        return parameter;
                    }
                }
            }
            case RETURN_VALUE -> {
                if (pathNodes.isEmpty()) {// NOSONAR
                    return method;
                }
            }
            default -> {
                // do nothing
            }
        }
        throw new IllegalStateException(
                "Unexpected property path: propertyPath=" + getDebugPropertyPath(context) + ", index=" + index);
    }

    private List<Path.Node> getPathNodes(MessageInterpolatorContext context) {
        Path propertyPath = context.getPropertyPath();
        List<Path.Node> pathNodes = new ArrayList<>();
        for (Path.Node node : propertyPath) {
            pathNodes.add(node);
        }
        if (pathNodes.isEmpty()) {
            throw new IllegalStateException("Unexpected empty property path");
        }

        Path.Node rootNode = pathNodes.get(0);
        if (rootNode.getKind() != ElementKind.BEAN) {
            throw new IllegalStateException("Invalid property path: " + getDebugPropertyPath(context));
        }
        return pathNodes;
    }

    public Class<?> getValidationTargetType(MessageInterpolatorContext context) {
    }

    public String getDebugPropertyPath(MessageInterpolatorContext context) {
        StringBuilder debugPropertyPath = new StringBuilder();
        Path propertyPath = context.getPropertyPath();
        for (Path.Node node : propertyPath) {
            if (!debugPropertyPath.isEmpty()) {
                debugPropertyPath.append("/");
            }
            appendDebugPropertyPath(debugPropertyPath, node);
        }
        return debugPropertyPath.toString();
    }

    private void appendDebugPropertyPath(StringBuilder debugPropertyPath, Path.Node node) {
        debugPropertyPath.append(node.getClass().getSimpleName());
        debugPropertyPath.append("(kind=");
        debugPropertyPath.append(node.getKind());
        debugPropertyPath.append(", name=");
        debugPropertyPath.append(node.getName());
        debugPropertyPath.append(", inIterable=");
        debugPropertyPath.append(node.isInIterable());
        if (node.getIndex() != null) {
            debugPropertyPath.append(", index=");
            debugPropertyPath.append(node.getIndex());
        }
        if (node.getKey() != null) {
            debugPropertyPath.append(", key=");
            debugPropertyPath.append(node.getKey());
        }
        switch (node.getKind()) {
            case BEAN -> {
                if (node instanceof Path.BeanNode beanNode) {// NOSONAR
                    appendDebugPropertyPathValues(debugPropertyPath, beanNode);
                }
            }
            case CONSTRUCTOR -> {
                if (node instanceof Path.ConstructorNode constructorNode) {// NOSONAR
                    appendDebugPropertyPathValues(debugPropertyPath, constructorNode);
                }
            }
            case CONTAINER_ELEMENT -> {
                if (node instanceof Path.ContainerElementNode containerElementNode) {// NOSONAR
                    appendDebugPropertyPathValues(debugPropertyPath, containerElementNode);
                }
            }
            case METHOD -> {
                if (node instanceof Path.MethodNode methodNode) {// NOSONAR
                    appendDebugPropertyPathValues(debugPropertyPath, methodNode);
                }
            }
            case PARAMETER -> {
                if (node instanceof Path.ParameterNode parameterNode) {// NOSONAR
                    appendDebugPropertyPathValues(debugPropertyPath, parameterNode);
                }
            }
            case PROPERTY -> {
                if (node instanceof Path.PropertyNode propertyNode) {// NOSONAR
                    appendDebugPropertyPathValues(debugPropertyPath, propertyNode);
                }
            }
            default -> {
                // do nothing
            }
        }
        debugPropertyPath.append(")");
    }

    private void appendDebugPropertyPathValues(StringBuilder debugPropertyPath, Path.BeanNode beanNode) {
        if (beanNode.getContainerClass() != null) {
            debugPropertyPath.append(", containerClass=");
            debugPropertyPath.append(beanNode.getContainerClass().getName());
        }
        if (beanNode.getTypeArgumentIndex() != null) {
            debugPropertyPath.append(", typeArgumentIndex=");
            debugPropertyPath.append(beanNode.getTypeArgumentIndex());
        }
    }

    private void appendDebugPropertyPathValues(StringBuilder debugPropertyPath, Path.ConstructorNode constructorNode) {
        debugPropertyPath.append(", parameterTypes=");
        debugPropertyPath.append(constructorNode.getParameterTypes().stream().map(Class::getName)
                .collect(Collectors.joining(",")));
    }

    private void appendDebugPropertyPathValues(StringBuilder debugPropertyPath,
            Path.ContainerElementNode containerElementNode) {
        if (containerElementNode.getContainerClass() != null) {
            debugPropertyPath.append(", containerClass=");
            debugPropertyPath.append(containerElementNode.getContainerClass().getName());
        }
        if (containerElementNode.getTypeArgumentIndex() != null) {
            debugPropertyPath.append(", typeArgumentIndex=");
            debugPropertyPath.append(containerElementNode.getTypeArgumentIndex());
        }
    }

    private void appendDebugPropertyPathValues(StringBuilder debugPropertyPath, Path.MethodNode methodNode) {
        debugPropertyPath.append(", parameterTypes=");
        debugPropertyPath.append(methodNode.getParameterTypes().stream().map(Class::getName)
                .collect(Collectors.joining(",")));
    }

    private void appendDebugPropertyPathValues(StringBuilder debugPropertyPath, Path.ParameterNode parameterNode) {
        debugPropertyPath.append(", parameterIndex=");
        debugPropertyPath.append(parameterNode.getParameterIndex());
    }

    private void appendDebugPropertyPathValues(StringBuilder debugPropertyPath, Path.PropertyNode propertyNode) {
        if (propertyNode.getContainerClass() != null) {
            debugPropertyPath.append(", containerClass=");
            debugPropertyPath.append(propertyNode.getContainerClass().getName());
        }
        if (propertyNode.getTypeArgumentIndex() != null) {
            debugPropertyPath.append(", typeArgumentIndex=");
            debugPropertyPath.append(propertyNode.getTypeArgumentIndex());
        }
    }
}