{{- define "template.fullpath" -}}
{{- if .Release.Namespace -}}
{{- printf "/%s/%s" .Release.Namespace .Chart.Name -}}
{{- else -}}
{{- printf "/%s" .Chart.Name -}}
{{- end -}}
{{- end -}}

{{- define "template.nameWithNamespace" -}}
{{- if .Release.Namespace -}}
{{- printf "%s.%s" .Chart.Name .Release.Namespace -}}
{{- else -}}
{{- printf "/%s" .Chart.Name -}}
{{- end -}}
{{- end -}}

{{/*
This template generates the image name for the deployment depending on the value of "repository" field in values.yaml file.
*/}}
{{- define "template.containerImage" -}}
{{- if .Values.image -}}
{{ .Values.image }}
{{- else -}}
{{- if .Values.imageRepo -}}
{{- if eq .Chart.AppVersion "" -}}
{{ .Values.imageRepo }}/{{ .Chart.Name }}
{{- else -}}
{{ .Values.imageRepo }}/{{ .Chart.Name }}:{{ .Chart.AppVersion }}
{{- end }}
{{- else -}}
{{- if eq .Chart.AppVersion "" -}}
{{ .Chart.Name }}
{{- else -}}
{{ .Chart.Name }}:{{ .Chart.AppVersion }}
{{- end }}
{{- end }}
{{- end -}}
{{- end -}}