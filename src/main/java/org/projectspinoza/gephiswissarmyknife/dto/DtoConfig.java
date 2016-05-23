package org.projectspinoza.gephiswissarmyknife.dto;

import com.google.inject.Singleton;

@Singleton
public class DtoConfig {
  
  // mysql cred properties
  public static String mysqlHost;
  public static int mysqlPort;
  public static String mysqlUserName;
  public static String mysqlUserPassword;
  public static String mysqlDatabaseName;
  public static String mysqlTableName;
  public static String mysqlDataColumnName;

  // mongodb cred properties
  public static String mongodbHost;
  public static int mongodbPort;
  public static String mongodbDatabaseName;
  public static String mongodbCollectionName;
  public static String mongodbFieldName;
  public static String mongdbUserName;
  public static String mongodbUserPassword;


  // server conf
  public static int serverPort;
  public static String serverHost;

  // elasticsearch conf
  public static String elasticsearchHost;
  public static int elasticsearchPort;
  public static String elasticSearchClusterName;
  public static String elasticsearchIndex;
  public static String elasticsearchIndexType;

  // input file (standard Graph Format)
  public static String graphfileName;
  
  // raw Text file
  public static String textfileName;

  // custom
  public static String dataSource;
  public static String searchValue;
  
  public static boolean autoColor = true;
  public static int R = 205;
  public static int G = 220;
  public static int B = 213;
  

  public DtoConfig() {
  }
  
  public String getMysqlUserPassword() {
    return mysqlUserPassword;
  }

  public void setMysqlUserPassword(String mysqlUserPassword) {
    DtoConfig.mysqlUserPassword = mysqlUserPassword;
  }

  public String getMysqlDatabaseName() {
    return mysqlDatabaseName;
  }

  public void setMysqlDatabaseName(String mysqlDatabaseName) {
    DtoConfig.mysqlDatabaseName = mysqlDatabaseName;
  }

  public String getMysqlTableName() {
    return mysqlTableName;
  }

  public void setMysqlTableName(String mysqlTableName) {
    DtoConfig.mysqlTableName = mysqlTableName;
  }

  public String getMysqlDataColumnName() {
    return mysqlDataColumnName;
  }

  public void setMysqlDataColumnName(String mysqlDataColumnName) {
    DtoConfig.mysqlDataColumnName = mysqlDataColumnName;
  }

  public void setMysqlUserName(String mysqlUserName) {
    DtoConfig.mysqlUserName = mysqlUserName;
  }

  public String getDataSource() {
    return dataSource;
  }

  public void setDataSource(String dataSource) {
    DtoConfig.dataSource = dataSource;
  }

  public String getSearchValue() {
    return searchValue;
  }

  public void setSearchValue(String searchValue) {
    DtoConfig.searchValue = searchValue;
  }

  public String getMysqlHost() {
    return mysqlHost;
  }

  public void setMysqlHost(String mysqlHost) {
    DtoConfig.mysqlHost = mysqlHost;
  }

  public String getElasticsearchHost() {
    return elasticsearchHost;
  }

  public void setElasticsearchHost(String elasticsearchHost) {
    DtoConfig.elasticsearchHost = elasticsearchHost;
  }

  public String getMongodbHost() {
    return mongodbHost;
  }

  public void setMongodbHost(String mongodbHost) {
    DtoConfig.mongodbHost = mongodbHost;
  }

  public int getServerPort() {
    return serverPort;
  }

  public void setServerPort(int serverPort) {
    DtoConfig.serverPort = serverPort;
  }

  public String getServerHost() {
    return serverHost;
  }

  public void setServerHost(String serverhost) {
    DtoConfig.serverHost = serverhost;
  }

  public int getElasticsearchPort() {
    return elasticsearchPort;
  }

  public void setElasticsearchPort(int elasticsearchPort) {
    DtoConfig.elasticsearchPort = elasticsearchPort;
  }

  public String getElasticsearchClusterName() {
    return elasticSearchClusterName;
  }

  public void setElasticsearchClusterName(String clusterName) {
    DtoConfig.elasticSearchClusterName = clusterName;
  }

  public String getElasticsearchIndex() {
    return elasticsearchIndex;
  }

  public void setElasticsearchIndex(String elasticsearchIndex) {
    DtoConfig.elasticsearchIndex = elasticsearchIndex;
  }

  public String getElasticsearchIndexType() {
    return elasticsearchIndexType;
  }

  public void setElasticsearchIndexType(String elasticsearchIndexType) {
    DtoConfig.elasticsearchIndexType = elasticsearchIndexType;
  }

  public int getMongodbPort() {
    return mongodbPort;
  }

  public void setMongodbPort(int mongodbPort) {
    DtoConfig.mongodbPort = mongodbPort;
  }

  public String getMongodbDatabaseName() {
    return mongodbDatabaseName;
  }

  public void setMongodbDatabaseName(String mongodbDatabaseName) {
    DtoConfig.mongodbDatabaseName = mongodbDatabaseName;
  }

  public String getMongodbCollectionName() {
    return mongodbCollectionName;
  }

  public void setMongodbCollectionName(String mongodbCollectionName) {
    DtoConfig.mongodbCollectionName = mongodbCollectionName;
  }

  public String getMongodbFieldName() {
    return mongodbFieldName;
  }

  public void setMongodbFieldName(String mongodbFieldName) {
    DtoConfig.mongodbFieldName = mongodbFieldName;
  }
  
  public String getMongdbUserName() {
    return mongdbUserName;
  }

  public void setMongdbUserName(String mongdbUserName) {
    DtoConfig.mongdbUserName = mongdbUserName;
  }

  public String getMongodbUserPassword() {
    return mongodbUserPassword;
  }

  public void setMongodbUserPassword(String mongodbUserPassword) {
    DtoConfig.mongodbUserPassword = mongodbUserPassword;
  }

  public int getMysqlPort() {
    return mysqlPort;
  }

  public void setMysqlPort(int mysqlPort) {
    DtoConfig.mysqlPort = mysqlPort;
  }

  public String getMysqlUserName() {
    return mysqlUserName;
  }
  

  @Override
  public String toString() {
    return "Configuration [mysqlHost=" + mysqlHost + ", mysqlPort=" + mysqlPort
        + ", mysqlUserName=" + mysqlUserName + ", mysqlUserPassword="
        + mysqlUserPassword + ", mysqlDatabaseName=" + mysqlDatabaseName
        + ", mysqlTableName=" + mysqlTableName + ", mysqlDataColumnName="
        + mysqlDataColumnName + ", mongodbHost=" + mongodbHost
        + ", mongodbPort=" + mongodbPort + ", mongodbDatabaseName="
        + mongodbDatabaseName + ", mongodbCollectionName="
        + mongodbCollectionName + ", mongodbFieldName=" + mongodbFieldName
        + ", serverPort=" + serverPort + ", serverHost=" + serverHost
        + ", elasticsearchHost=" + elasticsearchHost + ", elasticsearchPort="
        + elasticsearchPort + ", elasticSearchClusterName="
        + elasticSearchClusterName + ", elasticsearchIndex="
        + elasticsearchIndex + ", elasticsearchIndexType="
        + elasticsearchIndexType + ", graphfileName=" + graphfileName
        + ", textfileName = " + textfileName
        + ", nodecentralitythreshHold=" + ", pagerankthreshHold="
        + ", neighborcountThreshHold=" + ", edgecolorBy=" + ", edgeType="
        + ", nodeSizeBy=" + ", backColor=" + ", layoutAlgoName="
        + ", layoutAlgoIterations=" + ", layoutAlgoDistance="
        + ", forceAtlasLayoutIsConverged=" + ", forceAtlasLayoutSpeed="
        + ", forceAtlasLayoutInertia=" + ", forceAtlasLayoutGravity="
        + ", forceAtlasLayoutMaxDisplacement="
        + ", fruchtermanReingoldLayoutArea="
        + ", fruchtermanReingoldLayoutSpeed="
        + ", fruchtermanReingoldLayoutGravity=" + ", dataSource=" + dataSource
        + ", searchValue=" + searchValue + "]";
  }

  public String getGraphfileName() {
    return graphfileName;
  }

  public void setGraphfileName(String graphfileName) {
    DtoConfig.graphfileName = graphfileName;
  }

  public String getTextfileName() {
    return textfileName;
  }

  public void setTextfileName(String textfileName) {
    DtoConfig.textfileName = textfileName;
  }

}
