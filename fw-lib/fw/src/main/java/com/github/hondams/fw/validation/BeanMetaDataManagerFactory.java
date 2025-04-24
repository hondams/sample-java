package com.github.hondams.fw.validation;

import java.util.List;

import org.hibernate.validator.internal.engine.ConstraintCreationContext;
import org.hibernate.validator.internal.engine.MethodValidationConfiguration;
import org.hibernate.validator.internal.engine.groups.ValidationOrderGenerator;
import org.hibernate.validator.internal.metadata.BeanMetaDataManager;
import org.hibernate.validator.internal.metadata.BeanMetaDataManagerImpl;
import org.hibernate.validator.internal.metadata.provider.MetaDataProvider;
import org.hibernate.validator.internal.properties.javabean.JavaBeanHelper;
import org.hibernate.validator.internal.util.ExecutableHelper;
import org.hibernate.validator.internal.util.ExecutableParameterNameProvider;
import org.hibernate.validator.internal.util.TypeResolutionHelper;
import org.hibernate.validator.metadata.BeanMetaDataClassNormalizer;

public class BeanMetaDataManagerFactory {

    public BeanMetaDataManager createBeanMetaData() {
        TypeResolutionHelper typeResolutionHelper = new TypeResolutionHelper();

        ConstraintCreationContext constraintCreationContext;
        ExecutableHelper executableHelper = new ExecutableHelper( typeResolutionHelper );;
        ExecutableParameterNameProvider parameterNameProvider;
        JavaBeanHelper javaBeanHelper =
            new JavaBeanHelper( ValidatorFactoryConfigurationHelper.determineGetterPropertySelectionStrategy( hibernateSpecificConfig, properties, externalClassLoader ),
                    ValidatorFactoryConfigurationHelper.determinePropertyNodeNameProvider( hibernateSpecificConfig, properties, externalClassLoader ) );
        BeanMetaDataClassNormalizer beanMetaDataClassNormalizer;
        ValidationOrderGenerator validationOrderGenerator;
        List<MetaDataProvider> optionalMetaDataProviders;
        MethodValidationConfiguration methodValidationConfiguration;

        return new BeanMetaDataManagerImpl  (
                constraintCreationContext,
                executableHelper,
                parameterNameProvider,
                javaBeanHelper,
                beanMetaDataClassNormalizer,
                validationOrderGenerator,
                optionalMetaDataProviders,
                methodValidationConfiguration
        );  
    }
}
