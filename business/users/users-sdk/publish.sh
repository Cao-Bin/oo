#!/usr/bin/env bash
gradle -Pjar_artifact_id="user-sdk" -x test clean build publish --stacktrace