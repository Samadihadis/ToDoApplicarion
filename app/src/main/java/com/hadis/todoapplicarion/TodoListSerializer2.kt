package com.hadis.todoapplicarion

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.serialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class TodoListSerializer2(var dataSerializer: KSerializer<Todo>) :
    KSerializer<PersistentList<Todo>> {

    class PersistentListDescriptor : SerialDescriptor by serialDescriptor<List<Todo>>()

    override val descriptor = PersistentListDescriptor()

    override fun serialize(encoder: Encoder, value: PersistentList<Todo>) {
        return ListSerializer(dataSerializer).serialize(encoder, value.toList())
    }

    override fun deserialize(decoder: Decoder): PersistentList<Todo> {
return ListSerializer(dataSerializer).deserialize(decoder).toPersistentList()
    }

}