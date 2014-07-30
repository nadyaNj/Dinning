#!/bin/sh
# $Id: syncTest.sh 115740 2009-09-22 16:18:08Z bf $
#

# This is used for synchronizing with test version.

MY_DIR=`dirname $0`
cd ..
MY_HOME=`pwd`

REMOTE_USER=hhayryan
SERVER="edu.iunetworks.am"
VERSION=eduserver
echo "Synching $VERSION"

cd $MY_HOME
mvn -o -Dbuildhost=$VERSION -Dmaven.test.skip=true clean package

#
SRCDIR=$MY_HOME/target/dinning-web
DESTDIR=/export/home/hhayrayn/app/dinning

RSYNC=/usr/bin/rsync
SSH=/usr/bin/ssh
EXCLUDES=$MY_HOME/rsync/exclude.txt
INCLUDES=$MY_HOME/rsync/files.txt
#These options will make an update
#OPTS="-z -rc -vvHz --force --exclude-from="$EXCLUDES" --files-from="$INCLUDES" --rsync-path=/opt/csw/bin/rsync"
#These options will not update
OPTS="-z -rc -vvaHz --force --exclude-from="$EXCLUDES" --files-from="$INCLUDES" --rsync-path=/usr/bin/rsync"
#
$RSYNC $OPTS $SRCDIR $REMOTE_USER@$SERVER:$DESTDIR


echo "Recreating local workspace"
sleep 5
cd $MY_HOME
mvn -o -Dmaven.test.skip=true clean package