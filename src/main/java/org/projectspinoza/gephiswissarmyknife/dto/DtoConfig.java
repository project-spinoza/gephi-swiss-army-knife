package org.projectspinoza.gephiswissarmyknife.dto;

import com.google.inject.Singleton;

@Singleton
public class DtoConfig {

  // mysql cred properties
  private String mysqlHost;
  private int mysqlPort;
  private String mysqlUserName;
  private String mysqlUserPassword;
  private String mysqlDatabaseName;
  private String mysqlTableName;
  private String mysqlDataColumnName;

  // mongodb cred properties
  private String mongodbHost;
  private int mongodbPort;
  private String mongodbDatabaseName;
  private String mongodbCollectionName;
  private String mongodbFieldName;

  // server conf
  private int serverPort;
  private String serverHost;

  // elasticsearch conf
  private String elasticsearchHost;
  private int elasticsearchPort;
  private String elasticSearchClusterName;
  private String elasticsearchIndex;
  private String elasticsearchIndexType;

  // input file
  private String fileName;

  // custom
  private String dataSource;
  private String searchValue;

  public String getMysqlUserPassword() {
    return mysqlUserPassword;
  }

  public void setMysqlUserPassword(String mysqlUserPassword) {
    this.mysqlUserPassword = mysqlUserPassword;
  }

  public String getMysqlDatabaseName() {
    return mysqlDatabaseName;
  }

  public void setMysqlDatabaseName(String mysqlDatabaseName) {
    this.mysqlDatabaseName = mysqlDatabaseName;
  }

  public String getMysqlTableName() {
    return mysqlTableName;
  }

  public void setMysqlTableName(String mysqlTableName) {
    this.mysqlTableName = mysqlTableName;
  }

  public String getMysqlDataColumnName() {
    return mysqlDataColumnName;
  }

  public void setMysqlDataColumnName(String mysqlDataColumnName) {
    this.mysqlDataColumnName = mysqlDataColumnName;
  }

  public void setMysqlUserName(String mysqlUserName) {
    this.mysqlUserName = mysqlUserName;
  }

  public String getDataSource() {
    return dataSource;
  }

  public void setDataSource(String dataSource) {
    this.dataSource = dataSource;
  }

  public String getSearchValue() {
    return searchValue;
  }

  public void setSearchValue(String searchValue) {
    this.searchValue = searchValue;
  }

  private DtoConfig() {
  }

  public String getMysqlHost() {
    return mysqlHost;
  }

  public void setMysqlHost(String mysqlHost) {
    this.mysqlHost = mysqlHost;
  }

  public String getElasticsearchHost() {
    return elasticsearchHost;
  }

  public void setElasticsearchHost(String elasticsearchHost) {
    this.elasticsearchHost = elasticsearchHost;
  }

  public String getMongodbHost() {
    return mongodbHost;
  }

  public void setMongodbHost(String mongodbHost) {
    this.mongodbHost = mongodbHost;
  }

  public int getServerPort() {
    return serverPort;
  }

  public void setServerPort(int serverPort) {
    this.serverPort = serverPort;
  }

  public String getServerHost() {
    return serverHost;
  }

  public void setServerHost(String serverhost) {
    this.serverHost = serverhost;
  }

  public int getElasticsearchPort() {
    return elasticsearchPort;
  }

  public void setElasticsearchPort(int elasticsearchPort) {
    this.elasticsearchPort = elasticsearchPort;
  }

  public String getElasticsearchClusterName() {
    return elasticSearchClusterName;
  }

  public void setElasticsearchClusterName(String clusterName) {
    this.elasticSearchClusterName = clusterName;
  }

  public String getElasticsearchIndex() {
    return elasticsearchIndex;
  }

  public void setElasticsearchIndex(String elasticsearchIndex) {
    this.elasticsearchIndex = elasticsearchIndex;
  }

  public String getElasticsearchIndexType() {
    return elasticsearchIndexType;
  }

  public void setElasticsearchIndexType(String elasticsearchIndexType) {
    this.elasticsearchIndexType = elasticsearchIndexType;
  }

  public int getMongodbPort() {
    return mongodbPort;
  }

  public void setMongodbPort(int mongodbPort) {
    this.mongodbPort = mongodbPort;
  }

  public String getMongodbDatabaseName() {
    return mongodbDatabaseName;
  }

  public void setMongodbDatabaseName(String mongodbDatabaseName) {
    this.mongodbDatabaseName = mongodbDatabaseName;
  }

  public String getMongodbCollectionName() {
    return mongodbCollectionName;
  }

  public void setMongodbCollectionName(String mongodbCollectionName) {
    this.mongodbCollectionName = mongodbCollectionName;
  }

  public String getMongodbFieldName() {
    return mongodbFieldName;
  }

  public void setMongodbFieldName(String mongodbFieldName) {
    this.mongodbFieldName = mongodbFieldName;
  }

  public int getMysqlPort() {
    return mysqlPort;
  }

  public void setMysqlPort(int mysqlPort) {
    this.mysqlPort = mysqlPort;
  }

  public String getMysqlUserName() {
    return mysqlUserName;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
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
        + elasticsearchIndexType + ", fileName=" + fileName
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

}
