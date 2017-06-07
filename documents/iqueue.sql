insert into doctorinfo (dId, name, cId, profile,
preTreatId, inTreatId, afterTreatId) values("doc1378", "李子峰", "ks301", "主任医师 心导管室副主任", "pre1378", "in1378", "after1378")


update doctorinfo set startTime=Now(), endTime=Now() where dId = "doc5437"