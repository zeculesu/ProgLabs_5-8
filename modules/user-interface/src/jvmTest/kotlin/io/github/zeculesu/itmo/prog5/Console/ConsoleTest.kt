package io.github.zeculesu.itmo.prog5.Console

import io.github.zeculesu.itmo.prog5.manager.AddCommand
import kotlin.test.Test

import io.github.zeculesu.itmo.prog5.data.*
import io.github.zeculesu.itmo.prog5.manager.CommandSetMapImpl
import io.github.zeculesu.itmo.prog5.manager.CommandIOMemoryImpl
import io.github.zeculesu.itmo.prog5.manager.ConsoleCommandEnvironmentTestImpl
import kotlin.test.assertEquals

class ConsoleTest {
    @Test
    fun testConsole() {
        val io = CommandIOMemoryImpl(
            listOf(
                "name",
                "4 5",
                "342",
                "SUPPRESSOR",
                "HEAVY_BOLTGUN",
                "CHAIN_AXE",
                "name parentLegion"
            )
        )
        val collection = SpaceMarineCollection()
        val comm = AddCommand()
        val commandSet = CommandSetMapImpl(comm)
        val env = ConsoleCommandEnvironmentTestImpl(commandSet)
        comm.execute(collection, io, env, arrayOf())
        assertEquals(1, collection.size())
        val el = collection.get_by_id(1)
        assertEquals("name", el.name)
        assertEquals(Coordinates(4, 5f), el.coordinates)
        assertEquals(342, el.health)
        assertEquals(AstartesCategory.SUPPRESSOR, el.category)
        assertEquals(Weapon.HEAVY_BOLTGUN, el.weaponType)
        assertEquals(MeleeWeapon.CHAIN_AXE, el.meleeWeapon)
        assertEquals(Chapter("name", "parentLegion"), el.chapter)
    }
}