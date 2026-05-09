package moe.mohsen.nmusic.utils

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class UpdaterTest {
    @Test
    fun treatsAbbreviatedAndTaggedVersionsAsEqual() {
        assertTrue(Updater.isSameVersion("1.0", "1.0"))
        assertTrue(Updater.isSameVersion("1.0", "v1.0"))
        assertTrue(Updater.isSameVersion("1.0", "1.0.0"))
    }

    @Test
    fun stillDistinguishesDifferentPreReleaseVersions() {
        assertFalse(Updater.isSameVersion("1.0.0-beta.1", "1.0.0"))
    }
}