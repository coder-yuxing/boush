package com.dabanjia.boush.constant;

import lombok.Getter;

/**
 * jdbc类型与java类型映射
 *
 * @author GuangRen
 * @date 2019/11/19
 */
@Getter
public enum SqlMappingJavaTypeEnum {

    /**
     * 类型映射
     */
    CHAR_2_STRING("CHAR", "String", false, "java.lang.String"),

    VARCHAR_2_STRING("VARCHAR", "String", false, "java.lang.String"),

    DECIMAL_2_BIG_DECIMAL("DECIMAL", "BigDecimal", true, "java.math.BigDecimal"),

    TINYINT_2_INTEGER("TINYINT",  "Integer", false, "java.lang.Integer"),

    TINYINT_2_BOOLEAN("TINYINT", "Boolean",  false, "java.lang.Boolean"),

    BIGINT_2_LONG("BIGINT", "Long", false, "java.lang.Long"),

    DOUBLE_2_DOUBLE("DOUBLE", "Double", false, "java.lang.Double"),

    TIMESTAMP_2_DATE("TIMESTAMP", "Date", true, "java.util.Date"),

    INTEGER_2_INTEGER("INT", "Integer", false, "java.lang.Integer"),
    ;


    private String sqlType;

    private String javaType;

    private boolean needImport;

    private String className;

    SqlMappingJavaTypeEnum(String sqlType, String javaType, boolean needImport, String className) {
        this.sqlType = sqlType;
        this.javaType = javaType;
        this.needImport =  needImport;
        this.className = className;
    }

    /**
     * 根据sqlType查询
     * @param sqlType
     * @return
     */
    public static SqlMappingJavaTypeEnum getBySqlType(String sqlType) {
        if (sqlType != null && sqlType.length() > 0) {
            SqlMappingJavaTypeEnum[] values = SqlMappingJavaTypeEnum.values();
            for (SqlMappingJavaTypeEnum value : values) {
                if (value.sqlType.equalsIgnoreCase(sqlType)) {
                    return value;
                }
            }
        }
        return null;
    }
}
