/**
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

variable "terraform_credentials_file" {
  default = "~/secrets/terraform-admin.json"
}

variable "project" {}
variable "region" {
  default = "us-central1"
}

variable "ledger_topic" {
  default = "spez-ledger-topic"
}

variable "log_level" {
  default = "INFO"
}

variable "lpts_instance" {
  default = "spez-lpts-instance"
}
variable "lpts_database" {
  default = "spez-lpts-database"
}
variable "lpts_table" {
  default = "lpts"
}

variable "secret_name" {
  default = "service-account.json"
}

variable "spez_tailer_cluster" {
  default = "spez-tailer-cluster"
}

variable "sink_instance" {}
variable "sink_database" {}
variable "sink_table" {}

variable "tailer_image" {
  default = "gcr.io/spanner-event-exporter/spez:latest"
}

variable "timestamp_column" {}
variable "uuid_column" {}
