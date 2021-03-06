/*
 * Copyright (c) 2015. Rick Hightower, Geoff Chandler
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * QBit - The Microservice lib for Java : JSON, WebSocket, REST. Be The Web!
 */

package io.advantageous.qbit.bindings;

import io.advantageous.boon.Str;
import org.junit.Test;

import static io.advantageous.boon.Boon.puts;


public class MethodBindingTest {

    @Test
    public void test() {
        MethodBinding binding = new MethodBinding("GET", "bob", "/hi/how/{are}/{1}");
        puts(binding);

        Str.equalsOrDie("bob", binding.methodName());

        Str.equalsOrDie("/hi/how/", binding.address());

    }
}
