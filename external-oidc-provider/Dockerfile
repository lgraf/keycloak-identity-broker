FROM jboss/keycloak:4.8.3.Final

ADD external-oidc-provider-realm.json /opt/jboss/keycloak/
CMD ["-b", "0.0.0.0", "-Dkeycloak.import=/opt/jboss/keycloak/external-oidc-provider-realm.json", "-Dkeycloak.migration.action=export -Dkeycloak.migration.realmName=external-oidc-provider -Dkeycloak.migration.provider=singleFile -Dkeycloak.migration.file=/opt/jboss/keycloak/external-oidc-provider-realm-export.json"]