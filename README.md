####Command to start vault
**_vault server -config test.hcl_**

####Command to set vault Address
**_export VAULT_ADDR=http://127.0.0.1:8200_**

####Command to init vault
_**vault operator init**_

####Command to unseal vault
_**vault operator unseal**_


####Command to enable KV Secrets Engine
**_vault secrets enable -path=secret/ kv_**

####Command to put secret vault
**_vault kv put secret/vault-config example.username=demouser example.password=demopassword_**

####Command to get secret vault
**_vault kv get secret/vault-config_**

####Test.hcl contents

disable_mlock = true
listener "tcp" {
  address     = "127.0.0.1:8200"
  tls_disable = 1

}

path "secret/foo" {
  capabilities = ["read","write"]
}

path "secret/foo/" {
  capabilities = ["list"]
}

storage "file" {
  path = "/home/shreekar/vault/data"
}


####Export param for vault client
**_export VAULT_ADDR='http://127.0.0.1:8200'_**

**_export VAULT_TOKEN="s.L1TODuJ9l9T0uJhMdnc7znIB"_**



vault secrets enable mysql

vault write mysql/config/connection connection_url="root:admin123@tcp(127.0.0.1:3306)/"
	
	
	vault write mysql/roles/readonly \ db_name=mydb \sql="CREATE USER '{{name}}'@'localhost' IDENTIFIED BY '{{password}}';GRANT SELECT ON *.* TO '{{name}}'@'localhost';
	
	vault write mysql/roles/readonly \sql="CREATE USER '{{name}}'@'%' IDENTIFIED BY '{{password}}';GRANT SELECT ON *.* TO '{{name}}'@'%';"
	
	vault read mysql/creds/readonly
		

GRANT ALL PRIVILEGES ON *.* TO ''@'172.19.0.1' IDENTIFIED BY 'admin' WITH GRANT OPTION;

Vault plugin Database config since mysql plugin is deprecated

	
	vault write database/config/connection \
    plugin_name=mysql-database-plugin \
    connection_url="root:admin@tcp(172.28.0.2:3306)/" \
    allowed_roles="readonly"
    
    vault write database/roles/readonly \
    creation_statements="CREATE USER '{{name}}'@'%' IDENTIFIED BY '{{password}}';GRANT SELECT ON *.* TO '{{name}}'@'%';" \
    default_ttl="1h" \
    max_ttl="24h"
	
	vault read database/creds/readonly


Vault Configuration for AppRole based authentication with whitelist IP poperties

	vault secrets enable -path=my-secret/ kv

	vault write my-secret/kar-secret value='Hi Shreekar'

	vault list my-secret

	vault read my-secret/kar-secret

#Create hcl file and add below 

	path "my-secret/*" {
   	capabilities = ["read", "list"]
	}

	cat my-test.hcl

	vault policy write my-test my-test.hcl

	vault read /sys/policy/my-test

	vault token create -policy="my-test"

	vault list my-secret

	vault read my-secret/kar-secret

	vault write auth/approle/role/my-test secret_id_ttl=100m secret_id_num_uses=3 token_num_uses=3 token_ttl=10m token_max_ttl=30m policies=my-test secret_id_bound_cidrs=127.0.0.1/32
 
# To the above command u can add secret_id_bound_cidrs=<IP-ADDRESS>  to make it more secured

	vault read auth/approle/role/my-test


	vault read auth/approle/role/my-test/role-id

	vault write -f auth/approle/role/my-test/secret-id

