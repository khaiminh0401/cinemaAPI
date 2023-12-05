SELECT 
	lom.id, 
	lom.movieid, 
	lom.languageid,
	m.name AS movieName,
	l.name AS languageName
FROM languageofmovie lom
	JOIN movieconfig mc ON lom.movieid = mc.movieid
	JOIN movie m ON lom.movieid = m.id
	JOIN language l ON lom.languageid = l.id
WHERE mc.branchid = /* branchId */'cn0' AND (mc.status = '0' OR mc.status = '1')
-- 0 and 1 correspond to currently showing and upcoming