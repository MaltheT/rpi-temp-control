workspace("temp-monitor")
configurations({ "debug", "release" })

-- Output directories
outputdir = "%{cfg.buildcfg}"

-- Workspace-wide settings
language("C")
cdialect("C11")

-- Compiler flags
filter("system:windows")
buildoptions({ "/W4" })

filter("system:not windows")
buildoptions({ "-Wall", "-Wextra", "-Wpedantic" })

filter("configurations:debug")
defines({ "DEBUG" })
symbols("On")

filter("configurations:release")
defines({ "NDEBUG" })
optimize("On")

filter({})

-- Static library target
project("temp-monitor_lib")
kind("StaticLib")
location("build")
targetdir("build")
objdir("build/obj/" .. outputdir .. "/%{prj.name}")

files({
	"src/core/*.c",
	"src/networking/*.c",
	"src/utils/*.c",
})

removefiles({
	"src/core/main.c",
})

includedirs({
	"include",
})

-- Executable target
project("temp-monitor")
kind("ConsoleApp")
location("build")
targetdir("build")
objdir("build/obj/" .. outputdir .. "/%{prj.name}")

files({
	"src/core/main.c",
	"src/core/*.c",
	"src/networking/*.c",
	"src/utils/*.c",
})

removefiles({
	"src/core/main.c", -- Will be added back, but this prevents duplication
})

-- Re-add main.c explicitly
files({
	"src/core/main.c",
})

includedirs({
	"include",
})

-- Link libraries if needed
-- Uncomment and modify as needed:
-- links { "pthread" }

-- On Linux, you might need:
filter("system:linux")
-- links { "pthread", "dl" }

filter({})

-- Test project (uncomment when ready)
-- project "temp-monitor-tests"
--    kind "ConsoleApp"
--    location "build"
--    targetdir ("build/bin/" .. outputdir .. "/%{prj.name}")
--    objdir ("build/obj/" .. outputdir .. "/%{prj.name}")
--
--    files {
--       "tests/*.c"
--    }
--
--    includedirs {
--       "include"
--    }
--
--    links {
--       "temp-monitor_lib"
--    }
