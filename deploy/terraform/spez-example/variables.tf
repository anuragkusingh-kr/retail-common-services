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

variable "project" {}

variable "region" {
  default = "us-central1"
}

variable "tailer_image" {
  default = ""
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

variable "spez_tailer_cluster" {
  default = "spanner-event-exporter"
}

variable "sink_instance" {
  default = "event-sink-instance"
}

variable "sink_database" {
  default = "event-sink-database"
}

variable "sink_table" {
  default = "example"
}

variable "timestamp_column" {
  default = "CommitTimestamp"
}

variable "uuid_column" {
  default = "uuid"
}

variable "jmx_port" {
  default = "9010"
}